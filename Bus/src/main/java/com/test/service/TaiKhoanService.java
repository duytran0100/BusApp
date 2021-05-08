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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class TaiKhoanService {
    public boolean addTaiKhoan(String username,String password) throws SQLException {
        if (this.checkUserName(username) == false) // Nếu username không tồn tại thì mới làm
        {
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
            if(excuteUpdate > 0){
                conn.commit();
                return true;
            }
        }
        return false;
    }
    
    public boolean checkUserName(String username) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from taikhoan where username = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }
    
    public List<TaiKhoan> getTaiKhoan() throws SQLException{
        
            Connection conn = JdbcUtils.getConn();
            String sql = "Select * from taikhoan ";
            PreparedStatement stm = conn.prepareStatement(sql);
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
    
    public TaiKhoan getTaiKhoanById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from taikhoan where TaiKhoanID = ? ";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            TaiKhoan tk = new TaiKhoan();
            tk.setTaiKhoanId(rs.getInt("TaiKhoanID"));
            tk.setUserName(rs.getString("username"));
            tk.setPassWord(rs.getString("password"));
            tk.setLoaiTaiKhoan(rs.getInt("LoaiTaiKhoan"));
            
            return tk;
        }
        return null;
    }
    
    public TaiKhoan getTaiKhoanByUserName(String username) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from taikhoan where username = ? ";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            TaiKhoan tk = new TaiKhoan();
            tk.setTaiKhoanId(rs.getInt("TaiKhoanID"));
            tk.setUserName(rs.getString("username"));
            tk.setPassWord(rs.getString("password"));
            tk.setLoaiTaiKhoan(rs.getInt("LoaiTaiKhoan"));
            
            return tk;
        }
        return null;
    }
    
    public List<TaiKhoan> getTaiKhoan(String username) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from taikhoan where username like concat('%',?,'%') ";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();
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
    
    public boolean changePassword(String username, String password) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Update taikhoan set password = ? where username = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        String hash_password = DigestUtils.md5Hex(password);
        stm.setString(1, hash_password);
        stm.setString(2, username);
        
        if(stm.executeUpdate() > 0){
            conn.commit();
            return true;
        }
        return false;
    }
    
    public boolean checkLogin(String username, String password) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from taikhoan where username = ? and password = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        String hash_password = DigestUtils.md5Hex(password);
        stm.setString(1,username);
        stm.setString(2, hash_password);
        ResultSet rs = stm.executeQuery();
        
        return rs.next();
    }
    
    public boolean deleteTaiKhoan(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Delete from taikhoan where TaiKhoanID = ?";
        conn.setAutoCommit(false);
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        if(stm.executeUpdate() > 0 )
        {
            conn.commit();
            return true;
        }
        return false;
    }
}
