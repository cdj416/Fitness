package com.hongyuan.fitness.ui.webview;

import java.util.Map;

public class WebUrlUtil {

    public static String getParmsUrl(String url, Map<String,String> parms){
        String parmStr = "";

        if(!url.contains("?")){
            url = url+"?";
        }else{
            String lastChart = url.substring(url.length()-1);
            if(!lastChart.contains("?")){
                url = url + "&";
            }
        }
        for (Map.Entry<String, String> entry : parms.entrySet()) {
            parmStr+=entry.getKey()+"="+entry.getValue()+"&";
        }

        return url+parmStr.substring(0,parmStr.length()-1);
    }

}
