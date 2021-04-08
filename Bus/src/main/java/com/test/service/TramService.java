/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.Tram;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TramService {
    public List<Tram> getTram(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * from tram";
        if(kw != null && !kw.trim().isEmpty())
            sql+="where Name like concat('%',?,'%')";
        PreparedStatement stm = conn.prepareStatement(sql);
        if(kw != null && !kw.trim().isEmpty())
            stm.setString(1,kw.trim());
        ResultSet rs = stm.executeQuery();
        List<Tram> tram = new ArrayList<>();
        while(rs.next()){
            Tram t = new Tram();
            t.setTramId(rs.getInt("TramID"));
            t.setName(rs.getString("Name"));
            t.setDiaChi(rs.getString("DiaChi"));
            tram.add(t);
        }
        return tram;
    }
    public boolean addTram(Tram tram) {
        try {
            Connection conn = JdbcUtils.getConn();
            conn.setAutoCommit(false);
            String sql = "insert into tram(Name,DiaChi) values(?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,tram.getName());
            stm.setString(2, tram.getDiaChi());
            
            
            int excuteUpdate = stm.executeUpdate();
            return excuteUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteTram(int tramId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "DELETE FROM tram where TramID = ?";
        
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, tramId);
            if(stm.executeUpdate() == 1);
            {
             conn.commit();
             return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return false;
    }
    
    public Tram getTramById(int Id){
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "select * from tram where TramID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, Id);
            ResultSet rs = stm.executeQuery();
            Tram tram = new Tram();
            if (rs.next())
            {
                tram.setTramId(rs.getInt("TramID"));
                tram.setName(rs.getString("Name"));
                tram.setDiaChi(rs.getString("DiaChi"));
            }
            return tram;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
