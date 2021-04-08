/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.service.JdbcUtils;
import com.test.service.VeXeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
/**
 *
 * @author Admin
 */
public class VeXeTester {
    private static Connection conn;
    
    @BeforeClass
    public static void setUp() throws SQLException{
        conn = JdbcUtils.getConn();
    }
    
    @AfterClass
    public static void tearDown() throws SQLException{
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testKiemTraDat() throws SQLException{
        Assert.assertTrue(VeXeService.kiemTraDat(20, 74));
    }
    
//    @Test
//    public void testGetVeXeBySoGhe() throws SQLException{
//        Assert.assertNotNull(VeXeService.getVeXeBySoGhe(20, 74));
//         Assert.assertNull(VeXeService.getVeXeBySoGhe(20, 11));
//    }
}
