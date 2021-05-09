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
public class TaiKhoan {
    private int taiKhoanId;
    private String userName;
    private String passWord;
    private int loaiTaiKhoan;

    /**
     * @return the taiKhoanId
     */
    public int getTaiKhoanId() {
        return taiKhoanId;
    }

    /**
     * @param taiKhoanId the taiKhoanId to set
     */
    public void setTaiKhoanId(int taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return the loaiTaiKhoan
     */
    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    /**
     * @param loaiTaiKhoan the loaiTaiKhoan to set
     */
    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
    
    @Override
    public String toString() {
        return this.userName;
    }
}
