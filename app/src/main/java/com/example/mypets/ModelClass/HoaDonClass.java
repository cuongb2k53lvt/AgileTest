package com.example.mypets.ModelClass;

import java.util.Date;

public class HoaDonClass {
    private String mahoadon;
    private String tentk;
    private String makh;
    private Date ngaymua;
    private Double tongtien;

    public HoaDonClass() {
    }

    public HoaDonClass(String mahoadon, String tentk, String makh, Date ngaymua, Double tongtien) {
        this.mahoadon = mahoadon;
        this.tentk = tentk;
        this.makh = makh;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public Date getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(Date ngaymua) {
        this.ngaymua = ngaymua;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
}
