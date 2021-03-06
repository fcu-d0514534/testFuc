package com.shang.testfcu.JsonFetch;

import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.util.Log;

import com.shang.testfcu.FunctionListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Shang on 2017/3/18.
 */
public class BackgroundWorker2 extends AsyncTask<String,Void,String>{

    private Document document;
    private Elements elements;
    private FunctionListener functionListener;
    private static final String URL="http://taqm.epa.gov.tw/taqm/tw/default.aspx";

    public BackgroundWorker2(FunctionListener listener){
        functionListener=listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            document= Jsoup.connect(URL).get();
            elements=document.select("select").select("option");
            Log.d("Jsoup",elements.get(14).text()+"");
            return elements.get(14).text();

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        functionListener.showTextView(s);
    }


}
