package com.shang.testfcu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Shang on 2017/5/3.
 */
public class DBopneHelper extends SQLiteOpenHelper{

    public static String DATABASE_TABLE="demotable";
    public static String DATABASE_CREATETABLE="create table "+DATABASE_TABLE+" (sno,sna,sarea,ar,tot,sbi,bemp,lat,lng,mday,sv);";
    ArrayList<YouBike> youBikes;

    public DBopneHelper(Context context, ArrayList<YouBike> youBikes) {
        super(context,"demo.db", null, 1);
        this.youBikes=youBikes;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATETABLE);
        ContentValues cv;
        YouBike youBike;
        for(int i=0;i<youBikes.size();i++){
            cv=new ContentValues();
            youBike=youBikes.get(i);
            cv.put("sno",youBike.getSno());
            cv.put("sna",youBike.getSna());
            cv.put("sarea",youBike.getSarea());
            cv.put("ar",youBike.getAr());
            cv.put("tot",youBike.getTot());
            cv.put("sbi",youBike.getSbi());
            cv.put("bemp",youBike.getBemp());
            cv.put("lat",youBike.getLat());
            cv.put("lng",youBike.getLng());
            cv.put("mday",youBike.getMday());
            cv.put("sv",youBike.getSv());
            db.insert(DATABASE_TABLE,null,cv);
            cv.clear();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
