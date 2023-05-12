package com.gre.lxl.utils;


import com.gre.lxl.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * html中的标签带内容  转成 内容去掉标签
 *
 * @author 23053
 * @date 2021/1/12 18:15
 */

public class HtmlUtil {

    /**
     *  html -> test 文本提前
     *
     * @param inputString
     * @return
     */
    public static String html2Text(String inputString) {
        if (StringUtils.isEmpty(inputString)) {
            return null;
        }
        String htmlStr = inputString;
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;

        Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script,
                    Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern
                    .compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern
                    .compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

            // 替换&amp;nbsp;
            textStr = textStr.replaceAll("&amp;", "").replaceAll("nbsp;", "");

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }


    /**
     * 获取真实ip
     *
     * @param request
     * @return ip
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip =null;
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null) {
            //对于通过多个代理的情况，最后IP为客户端真实IP,多个IP按照','分割
            int position = ip.indexOf(",");
            if (position > 0) {
                ip = ip.substring(0, position);
            }
        }
        return ip;
    }
}
