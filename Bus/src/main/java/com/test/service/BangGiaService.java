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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<BangGia> getBangGia(String kw) throws SQLException{
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
    
    public boolean addBangGia(BangGia bg){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "Insert into banggia(TuyenDuongID, GiaTien)"
                    + " values(?,?) ";
            conn.setAutoCommit(false);
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,bg.getTuyenDuong().getTuyenDuongId());
            stm.setFloat(2,bg.getGiaTien());
            
            int excuteUpdate = stm.executeUpdate();
            
            if (excuteUpdate > 0){
                conn.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteBangGia(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "DELETE FROM banggia where BangGiaID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        conn.setAutoCommit(false);
        int executeUpdate = stm.executeUpdate();
        if(executeUpdate > 0)
        {
            conn.commit();
            return true;
        }
        return false;
    }
}
