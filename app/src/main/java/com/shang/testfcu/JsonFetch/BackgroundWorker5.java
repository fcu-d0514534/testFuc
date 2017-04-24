package com.shang.testfcu.JsonFetch;

import android.os.AsyncTask;
import android.util.Log;

import com.shang.testfcu.FunctionListener;
import com.shang.testfcu.YouBike;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shang on 2017/4/15.
 */
public class BackgroundWorker5 extends AsyncTask<String,Void,String> {
    HttpURLConnection connection=null;
    private FunctionListener functionListener;
    String json=null;
    public BackgroundWorker5(FunctionListener listener){
        functionListener=listener;
    }

    @Override
    protected String doInBackground(String... params) {
        //http://huli.logdown.com/posts/206098-study-on-the-google-map-api-which-city-am-i
        try {
            URL url=new URL("http://maps.google.com/maps/api/geocode/json?latlng=23.920823,120.652914&language=zh-TW&sensor=true");
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(3000);
            connection.connect();

            InputStream input=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(input,"UTF-8"));
            String line="";
            StringBuffer sb=new StringBuffer("");
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            br.close();
            input.close();
            connection.disconnect();

            json=sb.toString();
            JSONObject json1=new JSONObject(json);
            JSONArray jsonAr1=json1.getJSONArray("results");
            JSONObject json2=jsonAr1.getJSONObject(0);
            JSONArray jsonAr2=json2.getJSONArray("address_components");
            JSONObject json3=jsonAr2.getJSONObject(4);
            return json3.toString();

        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        functionListener.showTextView(result);

    }

}
