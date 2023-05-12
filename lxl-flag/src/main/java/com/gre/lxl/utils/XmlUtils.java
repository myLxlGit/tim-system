package com.gre.lxl.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.TimeZone;

/**
 * XML工具类
 *
 * @author lxl
 * @date 2022/5/25 9:05
 */
@Slf4j
public class XmlUtils {

    public static final String XML_HERDER = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n";

    /**
     * 将对象直接转换成String类型的XML输出
     *
     * @param obj 类
     * @return string
     */
    public static <T> String convertToXml(T obj, Class<T> objClass) {
        XStream xStream = new XStream(new DomDriver());
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(objClass);
        String s = xStream.toXML(obj);
        return XML_HERDER + s;
    }

    /**
     * 将String类型的xml转换成对象
     *
     * @param clazz  类
     * @param xmlStr 字符串
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertXmlToObject(String xmlStr, Class<T> clazz) {
        XStream xStream = new XStream(new DomDriver());
        Type genericSuperclass = clazz.getGenericSuperclass();
        String typeName = genericSuperclass.getTypeName();
        try {
            Class<?> aClass = Class.forName(typeName);
            //添加当前类的父类(保证反序列化子继承父，不保证子继承父，父又继承父)
            xStream.processAnnotations(aClass);
        } catch (Exception e) {
         e.printStackTrace();
        }
        log.info("父类:{}",typeName);
        xStream.allowTypes(new Class[]{clazz});
        xStream.aliasSystemAttribute(null, "class");
        xStream.aliasSystemAttribute(null, "serialization");
        xStream.autodetectAnnotations(true);
        xStream.ignoreUnknownElements();
        xStream.processAnnotations(clazz);
        //注册BigDecimal类型转化器(防止空值转化错误)
        xStream.registerConverter(new CustomBigDecimalConverter());
        xStream.registerConverter(new CustomDateConverter("yyyy-MM-dd HH:mm:ss",
                new String[]{"yyyy-MM-dd","HH:mm:ss","yyyyMMdd","HHmmss","yyyyMMdd HHmmss","yyyyMMddHHmmss"},
                TimeZone.getTimeZone("Asia/Shanghai")));
        return (T) xStream.fromXML(xmlStr);
    }

}
