package com.shang.testfcu;

import android.content.Intent;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FunctionListener {


    private Button bt1,bt2,bt3,bt4,bt5,bt6;
    private TextView tv;
    private ArrayList<YouBike>  youBike;

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
    }

    private void init(){
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        bt5=(Button)findViewById(R.id.bt5);
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
