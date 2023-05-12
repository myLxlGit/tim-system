package com.gre.lxl;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2022/6/28 17:19
 */
public enum MsgType {

    /**
     * 线索管理消息
     */
    CLUE("线索管理","ClueDetails","common"),

    /**
     * 商机管理消息
     */
    NICHE("商机管理","BusinessDetails","common"),

    /**
     * 拜访管理消息
     */
    VISIT("拜访管理","VisitDetails","common"),

    /**
     * 客户评审消息  (境内，境外客户)
     */
    CUSTOMER("客户评审","ReviewDetails,INCST,ReviewOverseas,FORCST","review"),

    /**
     * 供方评审消息
     */
    VENDOR("供方评审","ReviewGDetails,INFAC,ReviewGOverseas,FORFAC","review"),

    /**
     * 合作方评审消息 （目前只有境内）
     */
    PARTNER("合作方评审","LogisticsDetails","common"),

    /**
     * 基建评审消息
     */
    CONSTRUCTION("基建评审","ConstructionDetails","common"),

    /**
     * 其他评审
     */
    OTHER("其他评审","OtherDetails","common"),

    /**
     * 投标管理消息
     */
    TENDER("投标管理","TenderDetails","common"),

    /**
     * 合同立项消息
     */
    CONTRACT("合同立项","ContractDetails","common"),

    /**
     * 异动事件
     */
    NEWS("异动事件","newsAbnormalEventDetail","common"),

    /**
     * 税务违法
     */
    TAX("税务违法","taxIllegalDetail","common"),

    /**
     * 物流工作台中，采购合同 （后续添加，在修改）
     */
    LOG_CONTRACT("物流工作台","logContractDetail","common"),

    /**
     * 期货套保
     */
    HEDGING("期货套保","hedgingInfoDetail,IODetails","partner");

    public final String name;

    public final String value;

    public final String type;


    MsgType(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public static MsgType of(String name){
        for (MsgType msgType : MsgType.values()) {
            if(name.equals(msgType.getName())){
                return msgType;
            }
        }
        return null;
    }

    public static String getAllValue(){
        return Arrays.stream(MsgType.values())
                .map(MsgType::getValue)
                .collect(Collectors.joining(","));
    }

    public static String getMatchName(String value){
        for (MsgType msgType : MsgType.values()) {
            if(msgType.getValue().contains(value)){
                return msgType.getName();
            }
        }
        return null;
    }

}
