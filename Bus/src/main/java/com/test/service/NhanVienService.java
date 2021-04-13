/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class NhanVienService {
    public NhanVien getNhanVienById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where NhanVienID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            NhanVien nv = new NhanVien();
            
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            
            return nv;
        }
        return null;
    }
}
