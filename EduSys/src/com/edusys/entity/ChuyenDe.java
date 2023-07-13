/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.entity;

public class ChuyenDe {

    String maCD;
    String tenCD;
    double hocPhi;
    int thoiLuong;
    String hinh;
    String motTa;

    public ChuyenDe() {
    }

    public ChuyenDe(String maCD, String tenCD, double hocPhi, int thoiLuong, String hinh, String motTa) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.hinh = hinh;
        this.motTa = motTa;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMotTa() {
        return motTa;
    }

    public void setMotTa(String motTa) {
        this.motTa = motTa;
    }

    @Override
    public String toString() {
        return this.tenCD;
    }

    @Override
    public boolean equals(Object obj) {
        ChuyenDe other = (ChuyenDe) obj;
        return other.getMaCD().equals(this.getMaCD());
    }
}
