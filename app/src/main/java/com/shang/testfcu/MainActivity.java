package com.shang.testfcu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.shang.testfcu.JsonFetch.BackgroundWorker;
import com.shang.testfcu.JsonFetch.BackgroundWorker2;
import com.shang.testfcu.JsonFetch.BackgroundWorker3;
import com.shang.testfcu.JsonFetch.BackgroundWorker4;
import com.shang.testfcu.JsonFetch.BackgroundWorker5;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FunctionListener {


    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7;
    private TextView tv;
    private ArrayList<YouBike>  youBike;

    SQLiteDatabase db;
    DBopneHelper dBopneHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        init();
        admob();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_weather();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_PM();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_data();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_AllYoubike();
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlemap(youBike);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city();
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLite();
            }
        });
    }

    private void init(){
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        bt5=(Button)findViewById(R.id.bt5);
        bt6=(Button)findViewById(R.id.bt6);
        bt7=(Button)findViewById(R.id.bt7);
        tv=(TextView)findViewById(R.id.tv);
    }

    private void admob(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build(); //測試用廣告
        Log.d("DEVICE_ID_EMULATOR",AdRequest.DEVICE_ID_EMULATOR);
        mAdView.loadAd(adRequest);
    }

    private void fetch_weather(){
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute();
    }



    private void fetch_PM(){
        BackgroundWorker2 backgroundWorker2=new BackgroundWorker2(this);
        backgroundWorker2.execute();
    }

    private void fetch_data(){
        BackgroundWorker3 backgroundWorker3=new BackgroundWorker3(this);
        backgroundWorker3.execute();

    }

    private void fetch_AllYoubike(){
        BackgroundWorker4 backgroundWorker4=new BackgroundWorker4(this);
        backgroundWorker4.execute();
    }

    private void googlemap(ArrayList youBike){
        Intent intent=new Intent(MainActivity.this,MapsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("youbike",youBike);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void city(){
        BackgroundWorker5 backgroundWorker5=new BackgroundWorker5(this);
        backgroundWorker5.execute();
    }

    private void SQLite(){
        dBopneHelper=new DBopneHelper(this,youBike);
        db=dBopneHelper.getWritableDatabase();

        StringBuffer sb=new StringBuffer("");
        Cursor cursor=db.rawQuery("select * from "+DBopneHelper.DATABASE_TABLE,null);
        String[] title=cursor.getColumnNames();
        for(int i=0;i<title.length;i++){
            sb.append(title[i]+"   ");
        }
        sb.append("\n");
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            sb.append(cursor.getString(cursor.getColumnIndex(title[0]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[1]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[2]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[3]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[4]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[5]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[6]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[7]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[8]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[9]))+" ");
            sb.append(cursor.getString(cursor.getColumnIndex(title[10]))+" ");
            sb.append("\n");
            cursor.moveToNext();
        }
        showTextView(sb.toString());

    }


    @Override
    public void showTextView(String str) {
        tv.setText(str);
    }

    @Override
    public void setYoubike(ArrayList<YouBike> youbike) {
        this.youBike=new ArrayList<>();
        this.youBike=youbike;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
