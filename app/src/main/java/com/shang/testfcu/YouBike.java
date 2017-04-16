package com.shang.testfcu;

import java.io.Serializable;

/**
 * Created by Shang on 2017/4/16.
 */
public class YouBike implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private int sno;
    private String sna;
    private String sarea;
    private String ar;
    private int tot;
    private int sbi;
    private int bemp;
    private double lat;
    private double lng;
    private String mday;
    private int sv;

    public void setSno(int sno) {
        this.sno = sno;
    }
    public void setSna(String sna) {
        this.sna = sna;
    }
    public void setSarea(String sarea) {
        this.sarea = sarea;
    }
    public void setAr(String ar) {
        this.ar = ar;
    }
    public void setTot(int tot) {
        this.tot = tot;
    }
    public void setSbi(int sbi) {
        this.sbi = sbi;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public void setBemp(int bemp) {
        this.bemp = bemp;
    }
    public void setMday(String mday) {
        this.mday = mday;
    }
    public void setSv(int sv) {
        this.sv = sv;
    }

    public int getSno() {
        return sno;
    }
    public String getSna() {
        return sna;
    }
    public String getSarea() {
        return sarea;
    }
    public String getAr() {
        return ar;
    }
    public int getTot() {
        return tot;
    }
    public int getSbi() {
        return sbi;
    }
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
    public int getBemp() {
        return bemp;
    }
    public String getMday() {
        return mday;
    }
    public int getSv() {
        return sv;
    }

}
