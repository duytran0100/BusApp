/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.KhachHang;
import com.test.service.JdbcUtils;
import com.test.service.KhachHangService;
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
public class KhachHangTester {
    private static Connection conn;
    private static TaiKhoanService tkService;
    private static KhachHangService khService;
    
    @BeforeClass
    public static void setUp() throws SQLException{
        conn = JdbcUtils.getConn();
        tkService = new TaiKhoanService();
        khService = new KhachHangService();
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
    public void testAddKhachHang(){
        try {
            KhachHang kh = new KhachHang();
            kh.setHoDem("Tran Quoc");
            kh.setTen("Duy");
            kh.setEmail("duytran000@gmail.com");
            kh.setSdt("0909792905");
            kh.setTaiKhoan(tkService.getTaiKhoanById(1));
            boolean kq = khService.addKhachHang(kh);
            Assert.assertFalse(kq);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetKhachHang(){
        try {
            List<KhachHang> dsKhachHang = khService.getKhachHang();
            Assert.assertTrue(dsKhachHang.size() >= 1);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetKhachHangByPhone(){
        try {
            KhachHang kh = khService.getKhachHangByPhone("0909792905");
            Assert.assertNotNull(kh);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCheckKhachHang(){
        try {
            Assert.assertTrue(khService.checkKhachHang(1));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdateKhachHang(){
        try {
            KhachHang kh = new KhachHang();
            kh.setHoDem("Tran Quoc");
            kh.setTen("Duy");
            kh.setEmail("duytran0001@gmail.com");
            kh.setSdt("0909792905");
            kh.setDiaChi("Tay Ninh");
            kh.setTaiKhoan(tkService.getTaiKhoanById(1));
            boolean kq = khService.updateKhachHang(kh);
            
            Assert.assertTrue(kq);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
