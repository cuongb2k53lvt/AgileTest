package com.example.mypets.ModelClass;

public class NhanVienClass {
    private String tentk;
    private String tennv;
    private String mk;
    private String sdt;
    private String diachi;
    private String trangthai;

    public NhanVienClass(String tentk, String tennv, String mk, String sdt, String diachi, String trangthai) {
        this.tentk = tentk;
        this.tennv = tennv;
        this.mk = mk;
        this.sdt = sdt;
        this.diachi = diachi;
        this.trangthai = trangthai;
    }

    public NhanVienClass() {
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
