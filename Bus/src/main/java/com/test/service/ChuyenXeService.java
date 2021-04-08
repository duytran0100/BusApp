/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.ChuyenXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ChuyenXeService {
    public List<ChuyenXe> getChuyenXe() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from chuyenxe";
        Statement stm = conn.createStatement();
        List<ChuyenXe> dsChuyenXe = new ArrayList<>();
        ResultSet rs = stm.executeQuery(sql);
        XeService xeService = new XeService();
        while(rs.next()){
            ChuyenXe chuyenXe = new ChuyenXe();
            chuyenXe.setChuyenXeId(rs.getInt("ChuyenXeID"));
            chuyenXe.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            chuyenXe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            chuyenXe.setSoVe(rs.getInt("SoVe"));
            chuyenXe.setGiaTien(rs.getFloat("GiaTien"));
            chuyenXe.setXe(xeService.getXeById(rs.getInt("XeID")));
            
            dsChuyenXe.add(chuyenXe);
        }
        return dsChuyenXe;
    }
    
    public List<ChuyenXe> getChuyenXe(Date d, int tuyenDuongId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from chuyenxe where NgayKhoiHanh = ? and TuyenDuongID = ?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDate(1, (java.sql.Date) d);
        stm.setInt(2, tuyenDuongId);
        ResultSet rs = stm.executeQuery();
        List<ChuyenXe> dsChuyenXe = new ArrayList<>();
        TuyenDuongService cxService = new TuyenDuongService();
        XeService xService = new XeService();
        while(rs.next()){
            ChuyenXe chuyenXe = new ChuyenXe();
            
            chuyenXe.setChuyenXeId(rs.getInt("ChuyenXeID"));
            chuyenXe.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            chuyenXe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            chuyenXe.setGiaTien(rs.getFloat("GiaTien"));
            chuyenXe.setXe(xService.getXeById(rs.getInt("XeID")));
            chuyenXe.setSoVe(rs.getInt("SoVe"));
            chuyenXe.setTuyenDuong(cxService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            
            dsChuyenXe.add(chuyenXe);
        }
        return dsChuyenXe;
    }
    
    public  boolean addChuyenXe(ChuyenXe c){
        
        try {
            Connection conn = JdbcUtils.getConn();
            conn.setAutoCommit(false);
            
            String sql = "INSERT INTO chuyenxe(GioKhoiHanh,XeID,GiaTien,TuyenDuongID,NgayKhoiHanh,SoVe)" 
                    + "Values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setTime(1,c.getGioKhoiHanh());
            stm.setInt(2,c.getXe().getXeId());
            stm.setFloat(3,c.getGiaTien());
            stm.setInt(4, c.getTuyenDuong().getTuyenDuongId());
            stm.setDate(5, (java.sql.Date) c.getNgayKhoiHanh());
            stm.setInt(6, c.getSoVe());
            int executeUpdate = stm.executeUpdate();
            conn.commit();
            return executeUpdate > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return false;
    }
    
    public  ChuyenXe getChuyenByID(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "SELECT * FROM chuyenxe where ChuyenXeID = ?";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) // Kiểm tra có tồn tại chuyến xe không
        {
            ChuyenXe chuyenXe = new ChuyenXe();
            TuyenDuongService tdService = new TuyenDuongService();
            XeService xeService = new XeService();
            
            chuyenXe.setChuyenXeId(rs.getInt("ChuyenXeID"));
            chuyenXe.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            chuyenXe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            chuyenXe.setGiaTien(rs.getFloat("GiaTien"));
            chuyenXe.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            chuyenXe.setXe(xeService.getXeById(rs.getInt("XeID")));
            chuyenXe.setSoVe(rs.getInt("SoVe"));
            
            return chuyenXe;
        }
        
        return null;
    }
    
    public boolean deleteChuyenXe(int id) throws SQLException {
        
        Connection conn = JdbcUtils.getConn();
        String sql = "DELETE FROM chuyenxe where ChuyenXeID = ?";
        
     
        PreparedStatement stm = conn.prepareStatement(sql);
       
        stm.setInt(1, id);
        conn.setAutoCommit(false);
        if(stm.executeUpdate() == 1)
        {
            conn.commit();
            if(stm.executeUpdate() ==1)
                conn.commit();
            return true;
        }
        return false;
    
    }
}
