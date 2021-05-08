/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.service.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.test.pojo.ChuyenXe;
import com.test.pojo.TuyenDuong;
import com.test.pojo.Xe;
import com.test.service.ChuyenXeService;
import java.sql.Time;


/**
 *
 * @author Admin
 */
public class ChuyenXeTester {
    private static Connection conn;
   @BeforeClass
    public static void setUp() throws SQLException {
        conn = JdbcUtils.getConn();
    }
    
    @AfterClass
    public static void tearDown() {
        
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @Test
    public void testXeIdIsNotNull() {
        try {
            ChuyenXeService service = new ChuyenXeService();
            List<ChuyenXe> dsChuyenXe = service.getChuyenXe();
            dsChuyenXe.forEach(chuyenXe ->{
                Assert.assertNotNull(chuyenXe.getXe().getXeId());
            });
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCateCounter() {   
        try {
            ChuyenXeService service = new ChuyenXeService();
            List<ChuyenXe> cx = service.getChuyenXe();
            Assert.assertTrue(cx.size() >= 4);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    //Test getChuyenXe bằng ngày khời hành và tuyến đường
    public void testGetChuyenXe2(){
        try {
            ChuyenXeService service = new ChuyenXeService();
            List<ChuyenXe> dsChuyenXe;
            Date d = Date.valueOf("2021-05-18");
            dsChuyenXe = service.getChuyenXe(d, 1);
            System.out.println(dsChuyenXe.size());
            Assert.assertTrue(dsChuyenXe.size() >= 1);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @Test
    //Test getChuyenXe bằng ngày khởi hàn, trạm đi, trạm đến
    public void testGetChuyenXe3(){
        try {
            ChuyenXeService service = new ChuyenXeService();
            Date d = Date.valueOf("2021-05-18");
            List<ChuyenXe> dsChuyenXe = service.getChuyenXe(d,1,2);
            dsChuyenXe.forEach(cx ->{
                System.out.println(cx);
            });
            System.out.println(dsChuyenXe.size());
            Assert.assertTrue(dsChuyenXe.size() >= 1);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Test
    public void testGetChuyenByID(){
        try {
            ChuyenXeService service = new ChuyenXeService();
            System.out.println(service.getChuyenByID(3));
            Assert.assertNotNull(service.getChuyenByID(3));
            Assert.assertNull(service.getChuyenByID(5));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testAddChuyenXe(){
        ChuyenXeService service = new ChuyenXeService();
        ChuyenXe cx = new ChuyenXe();
        TuyenDuong td  = new TuyenDuong();
        td.setTuyenDuongId(1);
        Xe xe = new Xe();
        xe.setXeId(1);
        Time gioKhoiHanh = Time.valueOf("11:30:00");
        Date ngayKhoiHanh = Date.valueOf("2021-05-25");
        cx.setGioKhoiHanh(gioKhoiHanh);
        cx.setNgayKhoiHanh(ngayKhoiHanh);
        cx.setTuyenDuong(td);
        cx.setSoVe(25);
        cx.setGiaTien(100000);
        
        Assert.assertTrue(service.addChuyenXe(cx));
        
        
    }
    
    @Test
    public void testDeleteChuyenXe(){
        try {
            ChuyenXeService service = new ChuyenXeService();
            List<ChuyenXe> dsChuyenXe = service.getChuyenXe(Date.valueOf("2021-05-25"), 1);
            dsChuyenXe.forEach(cx ->{
                try {
                    Assert.assertTrue(service.deleteChuyenXe(cx.getChuyenXeId()));
                } catch (SQLException ex) {
                    Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
