package com.shang.testfcu.JsonFetch;

import android.os.AsyncTask;
import android.util.Log;

import com.shang.testfcu.FunctionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shang on 2017/3/18.
 */


public class BackgroundWorker3 extends AsyncTask<String,Void,String>{


    HttpURLConnection connection=null;
    URL url;
    StringBuffer sb;
    private FunctionListener functionListener;
    public BackgroundWorker3(FunctionListener listener){
        functionListener=listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            url=new URL("http://data.ntpc.gov.tw/api/v1/rest/datastore/382000000A-000352-001");
            connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setReadTimeout(3000);
            connection.connect();

            InputStream inputStream=connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

            String line="";
            sb=new StringBuffer("");
            while((line=bufferedReader.readLine())!=null){
                sb.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();

            return sb.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        //Log.d("JsonObject",result);

        try{
            JSONObject jsonObject=new JSONObject(result);

            //Log.d("JsonObject s",jsonObject.getString("success"));   //true
            //Log.d("JsonObject r",jsonObject.getString("result"));    //{"resource_id":"382000000A-00.....

            JSONObject jsonObject1=new JSONObject(jsonObject.getString("result"));
            JSONArray jsonArray=jsonObject1.getJSONArray("records");
            //Log.d("JsonObject",jsonArray.getString(0)+"");  //顯示陣列的第一個所以的值

            JSONObject jsonObject2=jsonArray.getJSONObject(0);
            StringBuffer sb=new StringBuffer("");
            sb.append(jsonObject2.getString("sno")).append("\n")
                    .append(jsonObject2.getString("sna")).append("\n")
                    .append(jsonObject2.getString("tot")).append("\n")
                    .append(jsonObject2.getString("sbi")).append("\n")
                    .append(jsonObject2.getString("sarea")).append("\n")
                    .append(jsonObject2.getString("mday")).append("\n")
                    .append(jsonObject2.getString("lat")).append("\n")
                    .append(jsonObject2.getString("lng")).append("\n")
                    .append(jsonObject2.getString("ar")).append("\n")
                    .append(jsonObject2.getString("sareaen")).append("\n")
                    .append(jsonObject2.getString("snaen")).append("\n")
                    .append(jsonObject2.getString("aren")).append("\n")
                    .append(jsonObject2.getString("bemp")).append("\n")
                    .append(jsonObject2.getString("act")).append("\n");

            //Log.d("JsonObject",jsonObject2.getString("sno"));  //成功1001
            //2017/3/25 終於解出來了


            functionListener.showTextView(sb.toString());

        }catch (JSONException e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
