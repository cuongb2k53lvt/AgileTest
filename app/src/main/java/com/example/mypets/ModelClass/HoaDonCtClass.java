package com.example.mypets.ModelClass;

public class HoaDonCtClass {
    private int mahdct;
    private String mahd;
    private String mapet;

    public HoaDonCtClass(int mahdct, String mahd, String mapet) {
        this.mahdct = mahdct;
        this.mahd = mahd;
        this.mapet = mapet;
    }

    public HoaDonCtClass() {
    }

    public int getMahdct() {
        return mahdct;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMapet() {
        return mapet;
    }

    public void setMapet(String mapet) {
        this.mapet = mapet;
    }
}
