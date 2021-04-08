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
public class NhanVien {
    private int nhanVienId;
    private String hoDem;
    private String ten;
    private String email;
    private String sdt;
    private String diaChi;
    private TaiKhoan taiKhoan;

    /**
     * @return the nhanVienId
     */
    public int getNhanVienId() {
        return nhanVienId;
    }

    /**
     * @param nhanVienId the nhanVienId to set
     */
    public void setNhanVienId(int nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    /**
     * @return the hoDem
     */
    public String getHoDem() {
        return hoDem;
    }

    /**
     * @param hoDem the hoDem to set
     */
    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the taiKhoan
     */
    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    
}
