package com.test.cookcook.data.entity;

/**
 * Created by NSH on 10/01/2018.
 */

public class Ingredients {
    private int idIngre; ///////////////Xem xet bo
    private String idCooked;
    private String name;
    private double amount; //So luong
    private String unit;    //Don vi

    public Ingredients(int idIngre, String idCooked, String name, double amount, String unit) {
        this.idIngre = idIngre;
        this.idCooked = idCooked;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredients() {
    }

    public String getIdCooked() {
        return idCooked;
    }

    public void setIdCooked(String idCooked) {
        this.idCooked = idCooked;
    }

    public int getIdIngre() {
        return idIngre;
    }

    public void setIdIngre(int idIngre) {
        this.idIngre = idIngre;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
