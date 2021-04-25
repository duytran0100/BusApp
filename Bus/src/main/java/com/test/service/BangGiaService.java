/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.BangGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BangGiaService {
    
    public List<BangGia> getBangGia() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from banggia";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<BangGia> dsBangGia = new ArrayList<>();
        TuyenDuongService tdService = new TuyenDuongService();
        while(rs.next()){
            BangGia gia = new BangGia();
            gia.setBangGiaId(rs.getInt("BangGiaID"));
            gia.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            gia.setGiaTien(rs.getFloat("GiaTien"));
            
            dsBangGia.add(gia);
        }
        return dsBangGia;
    }
    
    public BangGia getBangGiaByTuyenDuongID(int tuyenDuongId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from banggia where TuyenDuongID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, tuyenDuongId);
        ResultSet rs = stm.executeQuery();
        TuyenDuongService tdService = new TuyenDuongService();
        
        if(rs.next()){
            BangGia gia = new BangGia();
            gia.setBangGiaId(rs.getInt("BangGiaID"));
            gia.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            gia.setGiaTien(rs.getFloat("GiaTien"));
            
            return gia;
        }
        return null;
    }
}
