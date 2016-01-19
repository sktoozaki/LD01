package com.example.kk.ld01.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Title: LDResponse.java
 * @Package com.example.kk.ld01
 * @Description: LD01服务端响应解析类
 * @author JeanKK
 * @date 2016年1月14日
 * @version V0.1
 */

public class LDResponse {
    private String result;
    private JSONObject resultJson;
    private  int status;
    private  JSONObject data;


    public LDResponse(String result) {
        setresult(result);
    }

    private boolean getresult() {
        if (!result.equals("error")){
            return true;
        }else {
            return false;
        }
    }

    private void setresult(String result) {
        this.result=result;
        if (!result.equals("error")){
            try {
                resultJson =new JSONObject(result);
                Log.d("test", resultJson.toString());
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
        this.status =resultJson.getJSONObject("Result").getInt("Status");
    }

    public  JSONObject getData() {
        return data;
    }

    private void setData() throws JSONException {
        this.data =resultJson.getJSONObject("Result").getJSONObject("Data");
    }
}