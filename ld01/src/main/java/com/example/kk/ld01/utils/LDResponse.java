package com.example.kk.ld01.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Title: LDResponse.java
 * @Package com.example.kk.ld01
 * @Description: 觅来服务端响应解析类
 * @author guojian
 * @date 2016年1月14日
 * @version V0.1
 */

public class LDResponse {
    private String wcf_result;
    private JSONObject wcf_resultJson;
    private  int status;
    private  JSONObject data;


    public LDResponse(String wcf_result) {
        setWcf_result(wcf_result);
    }

    private boolean getWcf_result() {
        if (!wcf_result.equals("error")){
            return true;
        }else {
            return false;
        }
    }

    private void setWcf_result(String wcf_result) {
        this.wcf_result=wcf_result;
        if (!wcf_result.equals("error")){
            try {
                wcf_resultJson =new JSONObject(wcf_result);
                Log.d("test", wcf_resultJson.toString());
                setStatus();
                setData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
        }
    }

    public  int getStatus() {
        return status;
    }

    private void setStatus() throws JSONException {
        this.status =wcf_resultJson.getJSONObject("wcf_result").getInt("Status");
    }

    public  JSONObject getData() {
        return data;
    }

    private void setData() throws JSONException {
        this.data =wcf_resultJson.getJSONObject("wcf_result").getJSONObject("Data");
    }
}