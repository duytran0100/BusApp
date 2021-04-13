/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class KhachHangService {
    public KhachHang getKhachHangById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from khachhang where KhachHangID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            KhachHang kh = new KhachHang();
            
            kh.setKhachHangId(rs.getInt("NhanVienID"));
            kh.setHoDem(rs.getString("HoDem"));
            kh.setTen(rs.getString("Ten"));
            kh.setEmail(rs.getString("Email"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            
            return kh;
        }
        return null;
    }
}
