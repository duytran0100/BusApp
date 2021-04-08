/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.test.pojo.VeXe;
import com.test.pojo.ChuyenXe;
import com.test.pojo.Tram;
import com.test.pojo.TuyenDuong;
import com.test.pojo.Xe;

/**
 *
 * @author Admin
 */
public class VeXeService {
    public static boolean kiemTraDat(int soGhe, int ChuyenID) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "SELECT Count(SoGhe) FROM vexe WHERE ChuyenXeID = ? AND SoGhe = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, ChuyenID);
        stm.setInt(2, soGhe);
        ResultSet rs = stm.executeQuery();
        try {
            int s = 0;
            while(rs.next()){
                s= rs.getInt("COUNT(SoGhe)");
            }
            return s > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //chưa đặt
       return false;
    }
    
//    public static VeXeService getVeXeBySoGhe(int soGhe, int chuyenXeID) throws SQLException{
//        Connection conn = JdbcUtils.getConn();
//        String sql = "SELECT * FROM vexe where SoGhe = ? AND ChuyenXeID = ?";
//        //excute query
//        
//        try {
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1, soGhe);
//            stm.setInt(2, chuyenXeID);
//            ResultSet rs = stm.executeQuery();
//            while(rs.next()){
//            VeXe ve =new VeXe(rs.getInt("VeXeID"), ChuyenXeService.getChuyenByID(rs.getInt("ChuyenXeID")),
//                    rs.getString("Phone"), rs.getTime("GioDat"), rs.getString("KhachHangName"),rs.getInt("SoGhe"),rs.getInt("VeDat"));
//            return ve;
//        }
//        } catch (SQLException ex) {
//            Logger.getLogger(VeXeService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    return null;
//    }
//    
//    public static boolean addVeXe(VeXe v){
//       
//        Connection conn = JdbcUtils.getConn();
//        try {
//            conn.setAutoCommit(false);
//            String sql = "INSERT INTO vexe(ChuyenXeID, SoGhe, GioDat,KhachHangName,VeDat)" + "Values(?,?,?,?,?)";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1,v.getChuyenXe().getChuyenXeId());
//            stm.setInt(2,v.getSoGhe());
//            stm.setTime(3,v.getGioDat());
//            stm.setString(4, v.getKhachHang());
//            stm.setInt(5, v.getVeDat());
//            int executeUpdate = stm.executeUpdate();
//            conn.commit();
//            return executeUpdate > 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    
//    }
//    
//    public static boolean datVeXe(VeXe v){
//       
//        Connection conn = JdbcUtils.getConn();
//        try {
//            conn.setAutoCommit(false);
//            String sql = "INSERT INTO vexe(ChuyenXeID, SoGhe, GioDat,KhachHangName,VeDat)" + "Values(?,?,?,?,?)";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1,v.getChuyenXe().getChuyenXeID());
//            stm.setInt(2,v.getSoGhe());
//            stm.setTime(3,v.getGioDat());
//            stm.setString(4, v.getKhachHang());
//            stm.setInt(5,1);
//            int executeUpdate = stm.executeUpdate();
//            conn.commit();
//            return executeUpdate > 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    
//     //delete ve đặt
//    public static boolean deleteVeDat(int soGhe, int chuyenXeID) throws SQLException{
//        Connection conn = JdbcUtils.getConn();
//        VeXe v;
//        v = getVeXeBySoGhe(soGhe, chuyenXeID);
//        if(v != null){
//            if(v.getVeDat() == 1){
//                String sql = "DELETE FROM vexe where SoGhe = ? AND ChuyenXeID = ?";
//
//                try {
//                    conn.setAutoCommit(false);
//                    PreparedStatement stm = conn.prepareStatement(sql);
//                    stm.setInt(1, soGhe);
//                    stm.setInt(2, chuyenXeID);
//                    if(stm.executeUpdate() == 1);
//                    {
//                        conn.commit();
//                        return true;
//                     }
//            } catch (SQLException ex) {
//                Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
//        }
//      return false;
//    }
}
