/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.pojo;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Xe {
    private int xeId;
    private String bienSo;
    private int soGhe;
    private String loaiXe;
    private Date namSX;
    private Tram tram;

    /**
     * @return the xeId
     */
    public int getXeId() {
        return xeId;
    }

    /**
     * @param xeId the xeId to set
     */
    public void setXeId(int xeId) {
        this.xeId = xeId;
    }

    /**
     * @return the bienSo
     */
    public String getBienSo() {
        return bienSo;
    }

    /**
     * @param bienSo the bienSo to set
     */
    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    /**
     * @return the loaiXe
     */
    public String getLoaiXe() {
        return loaiXe;
    }

    /**
     * @param loaiXe the loaiXe to set
     */
    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    /**
     * @return the namSX
     */
    public Date getNamSX() {
        return namSX;
    }

    /**
     * @param namSX the namSX to set
     */
    public void setNamSX(Date namSX) {
        this.namSX = namSX;
    }

    /**
     * @return the tram
     */
    public Tram getTram() {
        return tram;
    }

    /**
     * @param tram the tram to set
     */
    public void setTram(Tram tram) {
        this.tram = tram;
    }
       
    @Override
    public String toString() {
        String result = String.format("%s (%s)",this.bienSo, this.loaiXe);
        return result;
    }
}
