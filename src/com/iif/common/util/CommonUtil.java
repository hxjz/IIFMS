package com.iif.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class CommonUtil {

    /**
     * 向页面中输出信息
     * 
     * @param response
     * @param msg
     * @param url
     */
    public static void printToPage(HttpServletResponse response, String msg,
            String url) {

        PrintWriter out = null;
        try {

            // HttpServletResponse responseServlet = ServletActionContext.getResponse();
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();

            msg = URLEncoder.encode(URLEncoder.encode(msg, "utf-8"), "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        out
                .write("<script language=\"javascript\" type=\"text/javascript\">window.location='"
                        + url + "?errorMessage=" + msg + "';</script>");

        out.flush();
        out.close();
    }

    /**
     * 判断JSONObject中是否存在指定的key
     * 
     * @param key
     * @param obj
     * @return
     */
    public static boolean isContainsKeyFromJson(String key, JSONObject obj) {

        if (obj.containsKey(key)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 从json中通过name获取值，这样可以防止发生异常
     * 
     * @param jsonObject
     * @param name
     * @return
     */
    public static String getJsonValue(JSONObject jsonObject, String name) {

        if (jsonObject.containsKey(name)) {
            return jsonObject.getString(name);
        } else {
            return null;
        }
    }
}
