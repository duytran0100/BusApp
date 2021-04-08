/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.pojo;

/**
 *
 * @author Admin
 */
public class BangGia {
    private int bangGiaId;
    private TuyenDuong tuyenDuong;
    private float giaTien;

    /**
     * @return the bangGiaId
     */
    public int getBangGiaId() {
        return bangGiaId;
    }

    /**
     * @param bangGiaId the bangGiaId to set
     */
    public void setBangGiaId(int bangGiaId) {
        this.bangGiaId = bangGiaId;
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
    
}
