package com.shang.testfcu;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shang on 2017/5/4.
 */
public class okHttpFetch {

    final OkHttpClient okHttpClient=new OkHttpClient();
    ArrayList<YouBike> youBikes;
    int i;
    private FunctionListener functionListener;

    public okHttpFetch(ArrayList<YouBike> youBike,FunctionListener listener){
        this.youBikes=youBike;
        functionListener=listener;

    }



    public void inputCity(){
        for (i=0;i<youBikes.size();i++){
            final Request request=new Request.Builder()
                    //"http://maps.google.com/maps/api/geocode/json?latlng=+24.071206,120.541735&language=zh-TW&sensor=true"
                    .url("http://maps.google.com/maps/api/geocode/json?latlng="+youBikes.get(i).getLat()+","+youBikes.get(i).getLng()
                            +"&language=zh-TW&sensor=true")
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result=response.body().string();
                    try {
                        JSONObject json1=new JSONObject(result);
                        JSONArray jsonAr1=json1.getJSONArray("results");
                        JSONObject json2=jsonAr1.getJSONObject(0);
                        JSONArray jsonAr2=json2.getJSONArray("address_components");
                        JSONObject json3=jsonAr2.getJSONObject(4);
                        String json=json3.getString("long_name");
                        Log.d("CITY",json);
                        youBikes.get(i).setCity(json);
                    } catch (JSONException e) {
                        //e.printStackTrace();
                    }
                }
            });
            //Log.d("ARRAYLIST",i+"");
        }
    }

    public void print(){
        StringBuffer sb=new StringBuffer("");
        for(i=0;i<youBikes.size();i++){
            sb.append(youBikes.get(i).getCity()+"\n");
        }
        functionListener.showTextView(sb.toString());
    }


}
