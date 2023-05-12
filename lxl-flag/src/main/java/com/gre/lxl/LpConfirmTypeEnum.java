package com.gre.lxl;

/**
 * @author lxl
 * @date 2022/7/12 17:08
 */
public enum LpConfirmTypeEnum {

    //来单确认业务类型  --> 申请信息查询接口
    //1-付款确认      -->  4-付款
    //2-承兑确认      -->  5-承兑
    //3-拒付         -->  6-拒付

    PAY("1","4"),

    ACCEPT("2","5"),

    REFUSE_PAY("3","6");

    private final String beforeConvert;
    private final String ofterConvert;

    LpConfirmTypeEnum(String beforeConvert, String ofterConvert) {
        this.beforeConvert = beforeConvert;
        this.ofterConvert = ofterConvert;
    }

    public static String getOfterConvert(String beforeConvert){
        for (LpConfirmTypeEnum lpConfirmTypeEnum : LpConfirmTypeEnum.values()) {
            if(beforeConvert.equals(lpConfirmTypeEnum.getBeforeConvert())){
                return lpConfirmTypeEnum.getOfterConvert();
            }
        }
        return null;
    }



    public String getBeforeConvert() {
        return beforeConvert;
    }

    public String getOfterConvert() {
        return ofterConvert;
    }
}
