/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.TuyenDuong;
import com.test.service.JdbcUtils;
import com.test.service.TramService;
import com.test.service.TuyenDuongService;
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
public class TuyenDuongTester {
    private static Connection conn;
    
    @BeforeClass
    public static void setUp() throws SQLException{
        conn = JdbcUtils.getConn();
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
    public void testNameIsNotNull() {
        try {
            TuyenDuongService service = new TuyenDuongService();
            List<TuyenDuong> dsTuyenDuong = service.getTuyenDuong("");
            dsTuyenDuong.forEach(tuyenDuong -> {
                Assert.assertNotNull(tuyenDuong.getTuyenDuongName());
                Assert.assertNotEquals("", tuyenDuong.getTuyenDuongName().trim());
            });
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTuyenDuongByID() throws SQLException{
        TuyenDuongService service = new TuyenDuongService();
        Assert.assertNotNull(service.getTuyenDuongById(1));
    }
    
    @Test
    public void testAddTuyenDuong(){
       TuyenDuong td = new TuyenDuong();
       TramService tService = new TramService();
       td.setTuyenDuongName("Tuyen Test");
       td.setTramDi(tService.getTramById(1));
       td.setTramDen(tService.getTramById(2));
       td.setKhoangCach(120);
       
       TuyenDuongService tdService = new TuyenDuongService();
       
        try {
            Assert.assertTrue(tdService.addTuyenDuong(td));
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDeleteTuyenDuong(){
        try {
            TuyenDuongService tdService = new TuyenDuongService();
            List<TuyenDuong> dsTuyenDuong = tdService.getTuyenDuong("Tuyen Test");
            
            dsTuyenDuong.forEach(td ->{
                try {
                    Assert.assertTrue(tdService.deleteTuyenDuong(td.getTuyenDuongId()));
                } catch (SQLException ex) {
                    Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }catch (SQLException ex) {
            Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
