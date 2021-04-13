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
        TuyenDuongService tdService = new TuyenDuongService();
        while(rs.next()){
            ChuyenXe chuyenXe = new ChuyenXe();
            chuyenXe.setChuyenXeId(rs.getInt("ChuyenXeID"));
            chuyenXe.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            chuyenXe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            chuyenXe.setSoVe(rs.getInt("SoVe"));
            chuyenXe.setGiaTien(rs.getFloat("GiaTien"));
            chuyenXe.setXe(xeService.getXeById(rs.getInt("XeID")));
            chuyenXe.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            
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
        TuyenDuongService tdService = new TuyenDuongService();
        XeService xService = new XeService();
        while(rs.next()){
            ChuyenXe chuyenXe = new ChuyenXe();
            
            chuyenXe.setChuyenXeId(rs.getInt("ChuyenXeID"));
            chuyenXe.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            chuyenXe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            chuyenXe.setGiaTien(rs.getFloat("GiaTien"));
            chuyenXe.setXe(xService.getXeById(rs.getInt("XeID")));
            chuyenXe.setSoVe(rs.getInt("SoVe"));
            chuyenXe.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            
            dsChuyenXe.add(chuyenXe);
        }
        return dsChuyenXe;
    }
    
    public List<ChuyenXe> getChuyenXe(Date d, int tramDi, int tramDen) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from chuyenxe cx where NgayKhoiHanh = ? and TuyenDuongID in "
                + "(select TuyenDuongID from tuyenduong where FromTram = ? and ToTram = ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDate(1, (java.sql.Date) d);
        stm.setInt(2, tramDi);
        stm.setInt(3, tramDen);
        ResultSet rs = stm.executeQuery();
        List<ChuyenXe> dsChuyenXe = new ArrayList<>();
        while(rs.next()){
            TuyenDuongService tdService = new TuyenDuongService();
            XeService xeService = new XeService();
            
            ChuyenXe cx = new ChuyenXe();
            cx.setChuyenXeId(rs.getInt("ChuyenXeID"));
            cx.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
            cx.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            cx.setXe(xeService.getXeById(rs.getInt("XeID")));
            cx.setTuyenDuong(tdService.getTuyenDuongById(rs.getInt("TuyenDuongID")));
            cx.setGiaTien(rs.getFloat("GiaTien"));
            cx.setSoVe(rs.getInt("SoVe"));
            
            dsChuyenXe.add(cx);
        }
        return dsChuyenXe;
    }
    
    public boolean addChuyenXe(ChuyenXe c){
        
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
    
    public ChuyenXe getChuyenByID(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from chuyenxe where ChuyenXeID = ?";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        if(rs.next()) // Kiểm tra có tồn tại chuyến xe không
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
        conn.setAutoCommit(false);
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        int executeUpdate = stm.executeUpdate();
        if(executeUpdate > 0)
        {
            conn.commit();
            return true;
        }
        return false;
    }
    
    public int soVeDaBan(int chuyenXeId){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "Select Count(ChuyenXeID) from vexe where ChuyenXeID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            int count = 0;
            stm.setInt(1, chuyenXeId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                count = rs.getInt("COUNT(ChuyenXeID)");
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
