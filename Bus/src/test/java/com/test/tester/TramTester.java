/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.Tram;
import com.test.service.JdbcUtils;
import com.test.service.TramService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;

/**
 *
 * @author Admin
 */
public class TramTester {
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
    public void testQuantity(){
        TramService s = new TramService();
        try {
            List<Tram> tram = s.getTram("");
            
            Assert.assertTrue(tram.size() >= 4);
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNameIsNotNull(){
        TramService s = new TramService();
        try {
            List<Tram> dsTram = s.getTram("");
            dsTram.forEach(tram -> {
                Assert.assertNotNull(tram.getName());
                Assert.assertNotEquals("",tram.getName().trim());
            });
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTramByID(){
        TramService s = new TramService();
        Tram t = s.getTramById(1);
        System.out.print(t);
        Assert.assertNotNull(t.getName());
        Assert.assertNotEquals("", t.getName().trim());
        Assert.assertNotNull(t.getDiaChi());
    }
    
    @Test
    public void testAddTram(){
        TramService s = new TramService();
        Tram t = new Tram();
        t.setName("Tram Test");
        t.setDiaChi("Test");
        Assert.assertTrue(s.addTram(t));
    }
    
    @Test
    public void testDelete() throws SQLException{
        TramService s = new TramService();
        List<Tram> dsTram = s.getTram("Tram Test");
        
        dsTram.forEach(tram ->{
            try {
                Assert.assertTrue(s.deleteTram(tram.getTramId()));
            } catch (SQLException ex) {
                Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
}
