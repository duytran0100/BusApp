/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.pojo;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChuyenXe {
    private int chuyenXeId;
    private Time gioKhoiHanh;
    private Date ngayKhoiHanh;
    private Xe xe;
    private float giaTien;
    private TuyenDuong tuyenDuong;
    private int soVe;

    
    /**
     * @return the ChuyenXeId
     */
    public int getChuyenXeId() {
        return chuyenXeId;
    }

    /**
     * @param ChuyenXeId the ChuyenXeId to set
     */
    public void setChuyenXeId(int ChuyenXeId) {
        this.chuyenXeId = ChuyenXeId;
    }

    /**
     * @return the gioKhoiHanh
     */
    public Time getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    /**
     * @param gioKhoiHanh the gioKhoiHanh to set
     */
    public void setGioKhoiHanh(Time gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    /**
     * @return the ngayKhoiHanh
     */
    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    /**
     * @param ngayKhoiHanh the ngayKhoiHanh to set
     */
    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    /**
     * @return the xe
     */
    public Xe getXe() {
        return xe;
    }

    /**
     * @param xe the xe to set
     */
    public void setXe(Xe xe) {
        this.xe = xe;
    }

    /**
     * @return the giaTien
     */
    public float getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    /**
     * @return the tuyenDuong
     */
    public TuyenDuong getTuyenDuong() {
        return tuyenDuong;
    }

    /**
     * @param tuyenDuong the tuyenDuong to set
     */
    public void setTuyenDuong(TuyenDuong tuyenDuong) {
        this.tuyenDuong = tuyenDuong;
    }

    /**
     * @return the soVe
     */
    public int getSoVe() {
        return soVe;
    }

    /**
     * @param soVe the soVe to set
     */
    public void setSoVe(int soVe) {
        this.soVe = soVe;
    }
    
    @Override
    public String toString() {
        return this.chuyenXeId + "."+this.tuyenDuong 
                +"("+ this.gioKhoiHanh + " " + this.ngayKhoiHanh +")";
    }
}
