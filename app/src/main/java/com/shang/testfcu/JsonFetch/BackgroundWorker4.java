package com.shang.testfcu.JsonFetch;

import android.os.AsyncTask;
import android.util.Log;

import com.shang.testfcu.FunctionListener;
import com.shang.testfcu.YouBike;
import com.shang.testfcu.okHttpFetch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shang on 2017/4/15.
 */
public class BackgroundWorker4 extends AsyncTask<String,Void,String> {

    private Elements elements;
    private Document doc;
    private Element e;
    private FunctionListener functionListener;
    private static final String URL="http://i.youbike.com.tw/cht/f11.php";
    ArrayList<YouBike> youBikes;
    YouBike youBike;

    public BackgroundWorker4(FunctionListener listener){
        functionListener=listener;
        youBikes=new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            doc= Jsoup.connect(URL).timeout(10000).get();
            elements=doc.getElementsByTag("script");
            e=elements.get(20);                                   //第20個節點
            List<DataNode> dataNode=e.dataNodes();
            Log.d("DATANODE",dataNode.get(0).getWholeData());

            StringBuffer sb=new StringBuffer("");
            sb.append(dataNode.get(0).getWholeData());

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        String[] str=result.split("siteContent");
        String s=str[2].substring(2,str[2].length()-2);   //剃好了 JSON格式

        StringBuffer stringBuffer=new StringBuffer("");
        int count=1;

        try {
            JSONObject jsonObject=new JSONObject(s);
            while(count<=7068) {                             //0001~7068 因為沒有照順序排
                String local = getLocal(count);
                try {
                    JSONObject youbike = jsonObject.getJSONObject(local);
                    stringBuffer.append(getYouBike(youbike));
                } catch (JSONException e) {                         //沒有每個都有 所以例外就跳回去不理她
                    count++;
                    //Log.d("JSONException", e.toString());
                    continue;
                }
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        functionListener.showTextView(stringBuffer.toString());
        functionListener.setYoubike(youBikes);
    }

    public String getLocal(int count){                        //int轉字串前面補0的處理
        StringBuffer sb=new StringBuffer("0000");
        sb.append(String.valueOf(count));
        String local=sb.substring(sb.length()-4,sb.length());
        return local;
    }

    public String getYouBike(JSONObject jsonObject){
        StringBuffer sb=new StringBuffer("");
        try {
            sb.append(jsonObject.getString("sno")+" ")
                    .append(jsonObject.getString("sna")+" ")
                    .append(jsonObject.getString("sarea")+" ")
                    .append(jsonObject.getString("ar")+" ")
                    .append(jsonObject.getString("tot")+" ")
                    .append(jsonObject.getString("sbi")+" ")
                    .append(jsonObject.getString("bemp")+" ")
                    .append(jsonObject.getString("lat")+" ")
                    .append(jsonObject.getString("lng")+" ")
                    .append(jsonObject.getString("mday")+" ")
                    .append(jsonObject.getString("sv")+" ")
                    .append("\n");
            setYoubike(jsonObject);

        } catch (JSONException e) {
            return "";
        }
        return sb.toString();
    }

    public void setYoubike(JSONObject jsonObject){
        youBike=new YouBike();
        try {
            youBike.setSno(jsonObject.getInt("sno"));
            youBike.setSna(jsonObject.getString("sna"));
            youBike.setSarea(jsonObject.getString("sarea"));
            youBike.setAr(jsonObject.getString("ar"));
            youBike.setTot(jsonObject.getInt("tot"));
            youBike.setSbi(jsonObject.getInt("sbi"));
            youBike.setBemp(jsonObject.getInt("bemp"));
            youBike.setLat(jsonObject.getDouble("lat"));
            youBike.setLng(jsonObject.getDouble("lng"));
            youBike.setMday(jsonObject.getString("mday"));
            youBike.setSv(jsonObject.getInt("sv"));
            youBikes.add(youBike);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

}
