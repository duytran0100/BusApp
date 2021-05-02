/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class XeService {
    public List<Xe> getXe(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from xe where lower(BienSo) like lower(concat('%',?,'%'))";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<Xe> dsXe = new ArrayList<>();
        TramService tService = new TramService();
        while(rs.next()){
            Xe xe = new Xe();
            xe.setXeId(rs.getInt("XeID"));
            xe.setBienSo(rs.getString("BienSo"));
            xe.setLoaiXe(rs.getString("LoaiXe"));
            xe.setNamSX(rs.getDate("NamSX"));
            xe.setSoGhe(rs.getInt("SoGhe"));
            xe.setTram(tService.getTramById(rs.getInt("TramID")));
            dsXe.add(xe);
        }
        return dsXe;
    }
    
    public boolean checkBienSo(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from xe where BienSo like ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        
        return rs.next();
    }
    
    public boolean addXe(Xe xe){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "Insert into Xe(BienSo,SoGhe,LoaiXe,NamSX,TramID)"
                    + " values(?,?,?,?,?) ";
            conn.setAutoCommit(false);
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, xe.getBienSo());
            stm.setInt(2,xe.getSoGhe());
            stm.setString(3, xe.getLoaiXe());
            stm.setDate(4, (java.sql.Date)xe.getNamSX());
            stm.setInt(5,xe.getTram().getTramId());
            
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
    
    public boolean deleteXe(int id) {
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "DELETE FROM xe WHERE XEID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            int execute = stm.executeUpdate();
                
            return execute > 0;
        } catch (SQLException ex) {
            Logger.getLogger(XeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Xe getXeById(int id){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "Select * from xe where XeID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            TramService tService = new TramService();
            if (rs.next()){
                Xe x = new Xe();
                x.setXeId(rs.getInt("XeID"));
                x.setBienSo(rs.getString("BienSo"));
                x.setSoGhe(rs.getInt("SoGhe"));
                x.setLoaiXe(rs.getString("LoaiXe"));
                x.setTram(tService.getTramById(rs.getInt("TramID")));
                x.setNamSX(rs.getDate("NamSX"));
                return x;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public Xe getXeByBienSo(String bienSo){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "Select * from xe where BienSo = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, bienSo);
            ResultSet rs = stm.executeQuery();
            TramService tService = new TramService();
            if (rs.next()){
                Xe x = new Xe();
                x.setXeId(rs.getInt("XeID"));
                x.setBienSo(rs.getString("BienSo"));
                x.setSoGhe(rs.getInt("SoGhe"));
                x.setLoaiXe(rs.getString("LoaiXe"));
                x.setTram(tService.getTramById(rs.getInt("TramID")));
                x.setNamSX(rs.getDate("NamSX"));
                return x;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
