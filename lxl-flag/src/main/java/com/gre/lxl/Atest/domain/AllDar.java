package com.gre.lxl.Atest.domain;

import lombok.Data;

/**
 * @author lxl
 * @date 2022/8/29 13:55
 */
@Data
public class AllDar {

    private String th;

    private String st;

    private String hand;

    private String say;
    public AllDar(Lor lor) {
        this.th = lor.getTh();
        this.st = lor.getSt();
        this.hand = lor.getHand();
        this.say = lor.getSay();
    }
}
