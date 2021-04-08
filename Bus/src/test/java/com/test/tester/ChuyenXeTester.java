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
//    @Test
//     public void testGetChuyenXe2() throws SQLException{
//       List <ChuyenXe> cx = ChuyenXeService.getChuyenXe(new Date(2020, 11,15 ), 1);
//       Assert.assertTrue(cx.size() >= 1);
//       for(ChuyenXe c: cx)
//           Assert.assertNotNull(c);
//    }
//     @Test
//    public void testGetChuyenXe3() throws SQLException{
//       List <ChuyenXe> cx = ChuyenXeService.getChuyenXe(new Date(2020, 11,14 ), 1,1);
//       Assert.assertTrue(cx.size() >= 1);
//       for(ChuyenXe c: cx)
//           Assert.assertNotNull(c.getChuyenXeID());
//    }
//    @Test
//    public void testCheckChuyenXe() throws SQLException{
//        Assert.assertEquals( 5, ChuyenXeService.CheckChuyenXe(2, new Date(2020, 11, 14)));
//    }
//    @Test
//    public void testGetChuyenByID(){
//        try {
//            Assert.assertNotNull(ChuyenXeService.getChuyenByID(60));
//            Assert.assertNull(ChuyenXeService.getChuyenByID(11133));
//        } catch (SQLException ex) {
//            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    @Test
//    public void testSoVeDaBan(){
//        Assert.assertEquals(2, ChuyenXeService.soVeDaBan(71));
//    }
//    @Test
//    public void testDeleteChuyenXe(){
//        try {
//            Assert.assertFalse(ChuyenXeService.deleteChuyenXe(71));
//            Assert.assertTrue(ChuyenXeService.deleteChuyenXe(60));
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ChuyenXeTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}
