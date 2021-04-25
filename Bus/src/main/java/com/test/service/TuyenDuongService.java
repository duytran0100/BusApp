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
import java.sql.Time;
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
                + " lower(TuyenDuongName) like lower(concat('%',?,'%'))";
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
            tuyenDuong.setKhoangCach(rs.getFloat("Distance"));
            tuyenDuong.setThoiGianDuKien(rs.getTime("TuyenDuongTime"));
            
            dsTuyenDuong.add(tuyenDuong);
        }
        return dsTuyenDuong;
    }
    
    public boolean addTuyenDuong(TuyenDuong td) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "insert into tuyenduong(TuyenDuongName,FromTram,ToTram,Distance,TuyenDuongTime)"
                + "values(?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1,td.getTramDi().getName() + "-" + td.getTramDen().getName());
        stm.setInt(2, td.getTramDi().getTramId());
        stm.setInt(3, td.getTramDen().getTramId());
        stm.setFloat(4, td.getKhoangCach());
        float t = td.getKhoangCach() / 48;
        int temp = (int) t;
        Time time = new Time(temp, (int) ((t -temp) * 60), 0);
        stm.setTime(5,time);
        int executeUpdate = stm.executeUpdate();
        if(executeUpdate > 0){
            conn.commit();
            return true;
        }
        return false;
    }
    
    public boolean deleteTuyenDuong(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "DELETE FROM tuyenduong where TuyenDuongID = ?";
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
