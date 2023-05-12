package com.gre.lxl.functionIn;

/**
 * @author lxl
 * @date 2022/3/17 11:09
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    void throwMessage(String message);

    default void eat() {
        System.out.println("很好吃");
    }
}
