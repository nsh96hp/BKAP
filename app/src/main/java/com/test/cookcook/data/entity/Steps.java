package com.test.cookcook.data.entity;

/**
 * Created by NSH on 10/01/2018.
 */

public class Steps {
    private int idSteps;    //Xem xet bo
    private int idCooked;   //Chuyen String
    private int num; //STT cua buoc dung de sau nay thay doi sap xep buoc
    private String name; //Ten buoc, noi dung buoc....
    private double time; //Thoi gian buoc
    private String unit; //Don vi thoi gian
    private String image;   //Anh cua buoc "co the co hoac khong".

    public Steps(int idSteps, int idCooked, int num, String name, double time, String unit, String image) {
        this.idSteps = idSteps;
        this.idCooked = idCooked;
        this.num = num;
        this.name = name;
        this.time = time;
        this.unit = unit;
        this.image = image;
    }

    public Steps() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getIdSteps() {
        return idSteps;
    }

    public void setIdSteps(int idSteps) {
        this.idSteps = idSteps;
    }

    public int getIdCooked() {
        return idCooked;
    }

    public void setIdCooked(int idCooked) {
        this.idCooked = idCooked;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
