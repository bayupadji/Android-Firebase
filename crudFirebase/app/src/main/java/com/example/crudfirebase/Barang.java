package com.example.crudfirebase;

import androidx.recyclerview.widget.RecyclerView;

public class Barang {
    private String Kdbrg, Nmbrg, Satuan, Hrgbeli, Hrgjual, Stok, Stok_min;
    private  String key;

    public Barang(){

    }

    public Barang(String kdbrg, String nmbrg, String satuan, String hrgbeli, String hrgjual, String stok, String stok_min) {
        Kdbrg = kdbrg;
        Nmbrg = nmbrg;
        Satuan = satuan;
        Hrgbeli = hrgbeli;
        Hrgjual = hrgjual;
        Stok = stok;
        Stok_min = stok_min;
    }

    public String getKdbrg() {
        return Kdbrg;
    }

    public void setKdbrg(String kdbrg) {
        Kdbrg = kdbrg;
    }

    public String getNmbrg() {
        return Nmbrg;
    }

    public void setNmbrg(String nmbrg) {
        Nmbrg = nmbrg;
    }

    public String getSatuan() {
        return Satuan;
    }

    public void setSatuan(String satuan) {
        Satuan = satuan;
    }

    public String getHrgbeli() {
        return Hrgbeli;
    }

    public void setHrgbeli(String hrgbeli) {
        Hrgbeli = hrgbeli;
    }

    public String getHrgjual() {
        return Hrgjual;
    }

    public void setHrgjual(String hrgjual) {
        Hrgjual = hrgjual;
    }

    public String getStok() {
        return Stok;
    }

    public void setStok(String stok) {
        Stok = stok;
    }

    public String getStok_min() {
        return Stok_min;
    }

    public void setStok_min(String stok_min) {
        Stok_min = stok_min;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
