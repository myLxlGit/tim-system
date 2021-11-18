package com.gre.lxl.common.util.sql;

import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.exception.BaseException;

/**
 * sql操作工具类
 *
 * @author sumec
 * @date 2020/09/01 00:00
 */
public class SqlUtil {
    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            throw new BaseException("参数不符合规范，不能进行查询");
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }
}
