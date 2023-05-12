package com.gre.lxl.fanshe;

import java.lang.reflect.Method;

/**
 * @author lxl
 * @date 2022/7/1 14:31
 */
public class FvTest {

    public static void main(String[] args) {
        try {
            FvData fvData = new FvData("ss", "dd");
            Class<FvData> fvDataClass = FvData.class;
            Class<?> aClass = Class.forName("com.gre.lxl.fanshe.FvData");
            Method[] declaredMethods = fvDataClass.getDeclaredMethods();
            for (Method method:declaredMethods) {
                method.invoke(fvData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
