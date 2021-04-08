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
public class VeXe extends ChuyenXe{
    private int veXeId;
    private ChuyenXe chuyenXe;
    private int soGhe;
    private Time gioDat;
    private Date ngayDat;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private byte veDat; // 1 là vé đã đặt, 0 là vé trống

    
    /**
     * @return the veXeId
     */
    public int getVeXeId() {
        return veXeId;
    }

    /**
     * @param veXeId the veXeId to set
     */
    public void setVeXeId(int veXeId) {
        this.veXeId = veXeId;
    }

    /**
     * @return the chuyenXe
     */
    public ChuyenXe getChuyenXe() {
        return chuyenXe;
    }

    /**
     * @param chuyenXe the chuyenXe to set
     */
    public void setChuyenXe(ChuyenXe chuyenXe) {
        this.chuyenXe = chuyenXe;
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
     * @return the gioDat
     */
    public Time getGioDat() {
        return gioDat;
    }

    /**
     * @param gioDat the gioDat to set
     */
    public void setGioDat(Time gioDat) {
        this.gioDat = gioDat;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the khachHang
     */
    public KhachHang getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     */
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    /**
     * @return the nhanVien
     */
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    /**
     * @param nhanVien the nhanVien to set
     */
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    /**
     * @return the veDat
     */
    public byte getVeDat() {
        return veDat;
    }

    /**
     * @param veDat the veDat to set
     */
    public void setVeDat(byte veDat) {
        this.veDat = veDat;
    }
    
}
