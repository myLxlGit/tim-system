package com.gre.lxl.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.constant.HttpStatus;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import com.gre.lxl.common.core.domain.BaseEntity;
import com.gre.lxl.common.core.page.PageDomain;
import com.gre.lxl.common.core.page.TableDataInfo;
import com.gre.lxl.common.core.page.TableSupport;
import com.gre.lxl.common.util.DateUtils;
import com.gre.lxl.common.util.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * web层通用数据处理
 *
 * @author sumec
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        } else {
            pageNum = 1;
            pageSize = 20;
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    protected <T extends BaseEntity> TableDataInfo getDataTable(List<T> list) {
        return getDataTable(list, item -> item);
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <T extends BaseEntity, R>TableDataInfo getDataTable(List<T> list, Function<T, R> fun) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");

        PageInfo pageInfo = new PageInfo(list);
        AtomicInteger i= new AtomicInteger();
        if (fun != null) {
            rspData.setRows(list.stream().map(item -> {
                item.setNo((pageInfo.getStartRow() + i.getAndIncrement()));
                return fun.apply(item);
            }).collect(Collectors.toList()));
        } else {
            rspData.setRows(list);
        }
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
