/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.tester;

import com.test.pojo.TaiKhoan;
import com.test.service.JdbcUtils;
import com.test.service.TaiKhoanService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class TaiKhoanTester {
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
    public void testAddTaiKhoan(){
        TaiKhoanService tk = new TaiKhoanService();
        boolean kt =tk.addTaiKhoan("duytran0100", "123456");
        Assert.assertEquals(kt,true);
    }
    
    @Test
    public void testUniqueUsername(){
        TaiKhoanService s = new TaiKhoanService();
        try {
            List<TaiKhoan> dsTaiKhoan = s.getTaiKhoan();
            List<String> username = new ArrayList<>();
            dsTaiKhoan.forEach(act -> {
                username.add(act.getUserName());
            });
            Set<String> username2 = new HashSet<>(username);
            
            Assert.assertEquals(username.size(), username2.size());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
