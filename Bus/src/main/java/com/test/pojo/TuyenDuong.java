/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.pojo;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class TuyenDuong {
    private int tuyenDuongId;
    private String tuyenDuongName;
    private Tram tramDi;
    private Tram tramDen;
    private float khoangCach;
    private Time thoiGianDuKien;

    /**
     * @return the tuyenDuongId
     */
    public int getTuyenDuongId() {
        return tuyenDuongId;
    }

    /**
     * @param tuyenDuongId the tuyenDuongId to set
     */
    public void setTuyenDuongId(int tuyenDuongId) {
        this.tuyenDuongId = tuyenDuongId;
    }

    /**
     * @return the tuyenDuongName
     */
    public String getTuyenDuongName() {
        return tuyenDuongName;
    }

    /**
     * @param tuyenDuongName the tuyenDuongName to set
     */
    public void setTuyenDuongName(String tuyenDuongName) {
        this.tuyenDuongName = tuyenDuongName;
    }

    /**
     * @return the tramDen
     */
    public Tram getTramDen() {
        return tramDen;
    }

    /**
     * @param tramDen the tramDen to set
     */
    public void setTramDen(Tram tramDen) {
        this.tramDen = tramDen;
    }

    /**
     * @return the tramDi
     */
    public Tram getTramDi() {
        return tramDi;
    }

    /**
     * @param tramDi the tramDi to set
     */
    public void setTramDi(Tram tramDi) {
        this.tramDi = tramDi;
    }

    /**
     * @return the khoangCach
     */
    public float getKhoangCach() {
        return khoangCach;
    }

    /**
     * @param khoangCach the khoangCach to set
     */
    public void setKhoangCach(float khoangCach) {
        this.khoangCach = khoangCach;
    }

    /**
     * @return the thoiGianDuKien
     */
    public Time getThoiGianDuKien() {
        return thoiGianDuKien;
    }

    /**
     * @param thoiGianDuKien the thoiGianDuKien to set
     */
    public void setThoiGianDuKien(Time thoiGianDuKien) {
        this.thoiGianDuKien = thoiGianDuKien;
    }

     @Override
    public String toString() {
        String result = String.format("%s(%s-%s)",this.tuyenDuongName,this.tramDi.getName(),this.tramDen.getName());
        return result;
    }
}
