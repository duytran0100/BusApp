/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.Xe;
import com.test.service.JdbcUtils;
import com.test.service.XeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class XeTester {
    private static Connection conn;
    
   @BeforeClass
    public static void setUp() throws SQLException {
        conn = JdbcUtils.getConn();
    }
    
    @AfterClass
    public static void tearDown() throws SQLException {
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNameIsNotNull(){
        XeService s = new XeService();
        try {
            List<Xe> dsXe = s.getXe("");
            dsXe.forEach(xe -> {
                Assert.assertNotNull(xe.getBienSo());
                Assert.assertNotEquals("",xe.getBienSo().trim());
            });
        } catch (SQLException ex) {
            Logger.getLogger(XeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testQuantity(){
        XeService s = new XeService();
        try {
            List<Xe> dsXe = s.getXe("");
            Assert.assertTrue(dsXe.size() >= 4);
        } catch (SQLException ex) {
            Logger.getLogger(XeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetXeByID(){
        XeService s = new XeService();
        Xe x = s.getXeById(1);
        Assert.assertNotNull(x.getBienSo());
        Assert.assertNotEquals("", x.getBienSo().trim());
        Assert.assertNotNull(x.getSoGhe());
    }
}
