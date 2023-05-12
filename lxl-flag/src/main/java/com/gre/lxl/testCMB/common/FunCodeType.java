package com.gre.lxl.testCMB.common;

import com.gre.lxl.common.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

/**
 * 招商银行有关接口类型
 *
 * @author lxl
 * @date 2022/5/30 15:56
 */
public class FunCodeType {

    /**
     * 信用证经办 ONB2DCOP
     */
    public final static String LC_ISSUE_INIT = "ONB2DCOP";

    /**
     * 补档 ONB2DCAD
     */
    public final static String LC_ADD = "ONB2DCAD";

    /**
     * 待补档列表查询 ONB2DCTK
     */
    public final static String LC_ADD_QUERY = "ONB2DCTK";

    /**
     * 信用证列表查询 ONB2DCQL
     */
    public final static String LC_QUERY_DETAILS = "ONB2DCQL";

    /**
     * 协议编号列表查询 ONB2DCQT
     */
    public final static String LC_AGREEMENT_NUMBER = "ONB2DCQT";

    /**
     * 授信列表查询 ONB2DCQE
     */
    public final static String LC_CREDIT = "ONB2DCQE";

    /**
     * 模式列表查询 ONB2DCMD
     */
    public final static String LC_BUS_MOD = "ONB2DCMD";

    /**
     * 改证经办 ONB2DCMO
     */
    public final static String LC_CHANGE = "ONB2DCMO";

    /**
     * 改证列表查询 ONB2DCML
     */
    public final static String LC_CHANGE_QUERY = "ONB2DCML";

    /**
     * 成功状态
     */
    public final static String SUCCESS = "SUC0000";

    public final static Map<String,String> map = new HashMap<>();

    static {
        map.put(LC_ISSUE_INIT,"信用证经办");
        map.put(LC_ADD,"补档");
        map.put(LC_ADD_QUERY,"待补档列表查询");
        map.put(LC_QUERY_DETAILS,"信用证列表查询");
        map.put(LC_AGREEMENT_NUMBER,"协议编号列表查询");
        map.put(LC_CREDIT,"授信列表查询");
        map.put(LC_BUS_MOD,"模式列表查询");
        map.put(LC_CHANGE,"改证经办");
        map.put(LC_CHANGE_QUERY,"改证列表查询");
    }

    /**
     * 返回接口名称
     *
     * @param funCode 功能编码
     * @return 返回接口名称
     */
    public static String getFunCodeName(String funCode) {
        boolean b = map.containsKey(funCode);
        if (!b) {
         throw new CustomException("招商银行不存在的功能编码");
        }
        return map.get(funCode);
    }
}
