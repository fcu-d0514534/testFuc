package com.shang.testfcu;

import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Shang on 2017/3/18.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String>{

    private Document document;
    private Elements elements;
    private FunctionListener functionListener;
    private static final String URL="http://www.cwb.gov.tw/V7/forecast/taiwan/inc/city/Taichung_City.htm";

    public BackgroundWorker(FunctionListener listener){
        functionListener=listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            document= Jsoup.connect(URL).get();
            elements=document.select("table.FcstBoxTable01").select("tr").select("th");
            String str=elements.get(0).text()
                    +"\n"+elements.get(1).text()
                    +"\n"+elements.get(2).text()
                    +"\n"+elements.get(3).text()
                    +"\n"+elements.get(4).text()
                    +"\n"+elements.get(5).text()
                    +"\n"+elements.get(6).text()
                    +"\n"+elements.get(7).text();

            elements=document.select("table.FcstBoxTable01").select("tr");
            Log.d("Jsoup",elements.eq(1).text());
            Log.d("Jsoup",elements.eq(2).text());

            return str;

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
