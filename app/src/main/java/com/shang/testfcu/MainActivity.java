package com.shang.testfcu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FunctionListener {


    private Button bt1,bt2,bt3,bt4,bt5,bt6;
    private TextView tv;

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

    }

    private void init(){
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        tv=(TextView)findViewById(R.id.tv);
    }

    private void fetch_weather(){
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute();
    }


    @Override
    public void showTextView(String str) {
        tv.setText(str);
    }
}
