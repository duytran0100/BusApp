/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.VeXe;
import com.test.service.ChuyenXeService;
import com.test.service.JdbcUtils;
import com.test.service.KhachHangService;
import com.test.service.VeXeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        boolean kq = veService.kiemTraDat(12, 1);
        
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
        
        Assert.assertNotNull(veService.getVeXeBySoGhe(12, 1));
    }
    
    @Test
    public void testCheckTimeDatVe() throws SQLException{
        VeXeService veService = new VeXeService();
        
        Assert.assertTrue(veService.CheckTimeDatVe(1));
    }
    
    @Test
    public void testAddVe() throws SQLException{
        VeXeService veXeService = new VeXeService();
        ChuyenXeService cxService = new ChuyenXeService();
        KhachHangService khService = new KhachHangService();
        
        VeXe veXe = new VeXe();
        veXe.setChuyenXe(cxService.getChuyenByID(1));
        veXe.setSoGhe(13);
        veXe.setKhachHang(khService.getKhachHangById(1));
        
        Assert.assertTrue(veXeService.addVeXe(veXe));
    }
    
    @Test
    public void testDoiVe() throws SQLException{
        VeXeService veXeService = new VeXeService();
        VeXe veCu = veXeService.getVeXeBySoGhe(13, 1);
        boolean kq = veXeService.doiVe(veCu, 14, 1, 1);
        
        Assert.assertTrue(kq);
    }
    
    @Test
    public void testHuyVe(){
        VeXeService veXeService = new VeXeService();
        boolean kq = veXeService.huyVeXe(14, 1);
        
        Assert.assertTrue(kq);
    }

}
