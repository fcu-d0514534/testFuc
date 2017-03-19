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
            StringBuffer str=new StringBuffer("");
            str.append(elements.text());

                    /*+"\n"+elements.get(1).text()
                    +"\n"+elements.get(2).text()
                    +"\n"+elements.get(3).text()
                    +"\n"+elements.get(4).text()
                    +"\n"+elements.get(5).text()
                    +"\n"+elements.get(6).text()
                    +"\n"+elements.get(7).text();*/

            elements=document.select("table.FcstBoxTable01").select("tr");
            str.append("\n").append(elements.eq(1).text()).append("\n").append(elements.eq(2).text());

            elements=document.select("table.FcstBoxTable01").select("tr").select("img");
            str.append("\n").append(elements.get(0).attr("title"))
                    .append("\n").append(elements.get(1).attr("title"))
                    .append("\n").append(elements.get(2).attr("title"))
                    .append("\n").append(elements.get(3).attr("title"))
                    .append("\n").append(elements.get(4).attr("title"))
                    .append("\n").append(elements.get(5).attr("title"))
                    .append("\n").append(elements.get(6).attr("title"))
                    .append("\n").append(elements.get(7).attr("title"))
                    .append("\n").append(elements.get(8).attr("title"))
                    .append("\n").append(elements.get(9).attr("title"))
                    .append("\n").append(elements.get(10).attr("title"))
                    .append("\n").append(elements.get(11).attr("title"))
                    .append("\n").append(elements.get(12).attr("title"))
                    .append("\n").append(elements.get(13).attr("title"));

            return str.toString();

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
