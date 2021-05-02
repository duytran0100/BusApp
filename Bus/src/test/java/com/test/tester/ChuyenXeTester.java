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
import com.test.service.ChuyenXeService;
import com.test.service.TuyenDuongService;
import com.test.service.XeService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            Date d = Date.valueOf("2020-12-14");
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
            Date d = Date.valueOf("2020-12-14");
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
            System.out.println(service.getChuyenByID(61));
            Assert.assertNotNull(service.getChuyenByID(61));
            Assert.assertNull(service.getChuyenByID(11133));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDeleteChuyenXe(){
        try {
            ChuyenXeService service = new ChuyenXeService();
            Assert.assertTrue(service.deleteChuyenXe(79));  
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
