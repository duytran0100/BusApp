/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.TuyenDuong;
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
public class TuyenDuongService {
    public List<TuyenDuong> getTuyenDuong(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from tuyenduong where"
                + " TuyenDuongName like concat('%',?,'%')";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<TuyenDuong> dsTuyenDuong = new ArrayList<>();
        TramService tramService = new TramService();
        while(rs.next()){
            TuyenDuong tuyenDuong = new TuyenDuong();
            tuyenDuong.setTuyenDuongId(rs.getInt("TuyenDuongID"));
            tuyenDuong.setTuyenDuongName(rs.getString("TuyenDuongName"));
            tuyenDuong.setTramDi(tramService.getTramById(rs.getInt("FromTram")));
            tuyenDuong.setTramDen(tramService.getTramById(rs.getInt("ToTram")));
            tuyenDuong.setTuyenKhuHoiId(rs.getInt("TuyenKhuHoiID"));
            tuyenDuong.setKhoangCach(rs.getFloat("Distance"));
            tuyenDuong.setThoiGianDuKien(rs.getTime("TuyenDuongTime"));
            
            dsTuyenDuong.add(tuyenDuong);
        }
        return dsTuyenDuong;
    }
    
    public TuyenDuong getTuyenDuongById(int tuyenDuongId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from tuyenduong where TuyenDuongID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, tuyenDuongId);
        ResultSet rs = stm.executeQuery();
        TramService tramService = new TramService();
        if (rs.next()){
           TuyenDuong tuyenDuong = new TuyenDuong();
           tuyenDuong.setTuyenDuongId(rs.getInt("TuyenDuongID"));
           tuyenDuong.setTuyenDuongName(rs.getString("TuyenDuongName"));
           tuyenDuong.setTramDi(tramService.getTramById(rs.getInt("FromTram")));
           tuyenDuong.setTramDen(tramService.getTramById(rs.getInt("ToTram")));
           tuyenDuong.setTuyenKhuHoiId(rs.getInt("TuyenKhuHoiID"));
           tuyenDuong.setKhoangCach(rs.getFloat("Distance"));
           tuyenDuong.setThoiGianDuKien(rs.getTime("TuyenDuongTime"));
           
           return tuyenDuong;
        }
        return null;
    }
    
    public List<TuyenDuong> getTuyenDuongByFromTram(int tramId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from tuyenduong where FromTram = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, tramId);
        ResultSet rs = stm.executeQuery();
        List<TuyenDuong> dsTuyenDuong = new ArrayList<>();
        TramService tramService = new TramService();
        while(rs.next()){
            TuyenDuong tuyenDuong = new TuyenDuong();
            tuyenDuong.setTuyenDuongId(rs.getInt("TuyenDuongID"));
            tuyenDuong.setTuyenDuongName(rs.getString("TuyenDuongName"));
            tuyenDuong.setTramDi(tramService.getTramById(rs.getInt("FromTram")));
            tuyenDuong.setTramDen(tramService.getTramById(rs.getInt("ToTram")));
            tuyenDuong.setTuyenKhuHoiId(rs.getInt("TuyenKhuHoiID"));
            tuyenDuong.setKhoangCach(rs.getFloat("Distance"));
            tuyenDuong.setThoiGianDuKien(rs.getTime("TuyenDuongTime"));
            
            dsTuyenDuong.add(tuyenDuong);
        }
        return dsTuyenDuong;
    }
}
