package com.shang.testfcu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

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
    }

    private void init(){
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        tv=(TextView)findViewById(R.id.tv);
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


    @Override
    public void showTextView(String str) {
        tv.setText(str);
    }

    @Override
    public void setYoubike(ArrayList<YouBike> youbike) {
        this.youBike=new ArrayList<>();
        this.youBike=youbike;
    }
}
