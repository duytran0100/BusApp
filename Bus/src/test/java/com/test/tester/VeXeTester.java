/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.VeXe;
import com.test.service.JdbcUtils;
import com.test.service.VeXeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.List;
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
        VeXeService veService = new VeXeService();
        boolean kq = veService.kiemTraDat(16, 85);
        
        Assert.assertTrue(kq);
    }
    
    @Test
    public void testGetVeXe() throws SQLException{
        VeXeService veXeService = new VeXeService();
        List<VeXe> dsVe = veXeService.getVeXe();
        
        Assert.assertTrue(dsVe.size() >= 1);
    }
    
    @Test
    public void testVeBySoGhe() throws SQLException{
        VeXeService veService = new VeXeService();
        
        Assert.assertNotNull(veService.getVeXeBySoGhe(16, 85));
    }
    
    @Test
    public void testCheckTimeDatVe() throws SQLException{
        VeXeService veService = new VeXeService();
        
        Assert.assertTrue(veService.CheckTimeDatVe(85));
    }

}
