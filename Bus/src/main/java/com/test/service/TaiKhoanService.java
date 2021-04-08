/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class TaiKhoanService {
    public boolean addTaiKhoan(String username,String password) {
        try {
            Connection conn = JdbcUtils.getConn();
            conn.setAutoCommit(false);
            String sql = "Insert into taikhoan(username,password,LoaiTaiKhoan)"
                    + "values(?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            String hash_password = DigestUtils.md5Hex(password);
            stm.setString(1, username);
            stm.setString(2, hash_password);
            stm.setInt(3, 0);
            int excuteUpdate = stm.executeUpdate();
            conn.commit();
            return excuteUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<TaiKhoan> getTaiKhoan() throws SQLException{
        
            Connection conn = JdbcUtils.getConn();
            String sql = "Select * from taikhoan";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            List<TaiKhoan> ds_taikhoan = new ArrayList<>();
            while(rs.next()){
                TaiKhoan tk = new TaiKhoan();
                tk.setTaiKhoanId(rs.getInt("TaiKhoanID"));
                tk.setUserName(rs.getString("username"));
                tk.setPassWord(rs.getString("password"));
                tk.setLoaiTaiKhoan(rs.getInt("LoaiTaiKhoan"));
                ds_taikhoan.add(tk);
            }
            return ds_taikhoan;
     
    }
}
