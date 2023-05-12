package com.gre.lxl.functionIn;

import com.gre.lxl.common.exception.CustomException;

/**
 * @author lxl
 * @date 2022/3/17 11:11
 */
public class VUtils {


    /**
     *  如果参数为true抛出异常
     *
     * @param b boolean
     * @return ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isTure(boolean b){
        return message -> {
            if (b){
            throw new CustomException(message);
        }
        };

//        return (errorMessage) -> {
//            if (b){
//                throw new CustomException(errorMessage);
//            }
//        };
    }

    public static int toInt() {
        return 1;
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return com.example.demo.func.BranchHandle
     **/
    public static BranchHandle isTureOrFalse(boolean b){

        return (trueHandle, falseHandle) -> {
            if (b){
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

}
