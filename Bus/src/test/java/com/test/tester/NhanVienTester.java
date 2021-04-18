/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.NhanVien;
import com.test.service.JdbcUtils;
import com.test.service.NhanVienService;
import com.test.service.TaiKhoanService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

/**
 *
 * @author Admin
 */
public class NhanVienTester {
    private static Connection conn;
    private static TaiKhoanService tkService;
    private static NhanVienService nvService;
    
    @BeforeClass
    public static void setUp() throws SQLException{
        conn = JdbcUtils.getConn();
        tkService = new TaiKhoanService();
        nvService = new NhanVienService();
    }
    
    @AfterAll
    public static void tearDown(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetNhanVien(){
        try {
            List<NhanVien> dsNhanVien = nvService.getNhanVien();
            Assert.assertTrue(dsNhanVien.size() >= 1);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testGetNhanVienByPhone(){
        try {
            NhanVien nv = nvService.getNhanVienByPhone("0886933092");
            Assert.assertNotNull(nv);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testAddNhanVien(){
        try {
            NhanVien nv = new NhanVien();
            nv.setHoDem("Tran Quoc");
            nv.setTen("Duy");
            nv.setEmail("duytran000@gmail.com");
            nv.setSdt("0909792905");
            nv.setTaiKhoan(tkService.getTaiKhoanById(7));
            boolean kq = nvService.addNhanVien(nv);
            Assert.assertFalse(kq);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdateNhanVien(){
        try {
            NhanVien nv = new NhanVien();
            nv.setHoDem("Tran");
            nv.setTen("Duy");
            nv.setEmail("duytran0100@gmail.com");
            nv.setSdt("0886933092");
            nv.setDiaChi("Binh Duong");
            nv.setTaiKhoan(tkService.getTaiKhoanById(7));
            boolean kq = nvService.updateNhanVien(nv);
            Assert.assertTrue(kq);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCheckNhanVien(){
        try {
            Assert.assertTrue(nvService.checkNhanVien(7));
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
