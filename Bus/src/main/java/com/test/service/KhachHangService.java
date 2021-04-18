/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.KhachHang;
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
public class KhachHangService {
    
    public KhachHang getKhachHangById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from khachhang where KhachHangID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            KhachHang kh = new KhachHang();
            
            kh.setKhachHangId(rs.getInt("KhachHangID"));
            kh.setHoDem(rs.getString("HoDem"));
            kh.setTen(rs.getString("Ten"));
            kh.setEmail(rs.getString("Email"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            
            return kh;
        }
        return null;
    }
    
    public List<KhachHang> getKhachHang() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from khachhang";
        PreparedStatement stm =  conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<KhachHang> dsKhachHang = new ArrayList<>();
        while(rs.next()){
            KhachHang kh = new KhachHang();
            TaiKhoanService tkService = new TaiKhoanService();
            kh.setKhachHangId(rs.getInt("KhachHangID"));
            kh.setHoDem(rs.getString("HoDem"));
            kh.setTen(rs.getString("Ten"));
            kh.setEmail(rs.getString("Email"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setTaiKhoan(tkService.getTaiKhoanById(rs.getInt("TaiKhoanID")));
            
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }
    
    public boolean addKhachHang(KhachHang kh) throws SQLException{
        if (this.checkKhachHang(kh.getTaiKhoan().getTaiKhoanId()))
            return false;
        
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Insert into khachhang(HoDem,Ten,Email,SDT,DiaChi,TaiKhoanID)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kh.getHoDem());
        stm.setString(2, kh.getTen());
        stm.setString(3, kh.getEmail());
        stm.setString(4, kh.getSdt());
        stm.setString(5, kh.getDiaChi());
        stm.setInt(6,kh.getTaiKhoan().getTaiKhoanId());
        
        if (stm.executeUpdate() > 0){
            conn.commit();
            return true;
        }
        
        return false;
    }
    
    public List<KhachHang> getKhachHang(String name) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from khachhang where Ten like concat('%',?,'%')";
        PreparedStatement stm =  conn.prepareStatement(sql);
        stm.setString(1,name);
        ResultSet rs = stm.executeQuery();
        List<KhachHang> dsKhachHang = new ArrayList<>();
        while(rs.next()){
            KhachHang kh = new KhachHang();
            TaiKhoanService tkService = new TaiKhoanService();
            kh.setKhachHangId(rs.getInt("KhachHangID"));
            kh.setHoDem(rs.getString("HoDem"));
            kh.setTen(rs.getString("Ten"));
            kh.setEmail(rs.getString("Email"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setTaiKhoan(tkService.getTaiKhoanById(rs.getInt("TaiKhoanID")));
            
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }
    
    public KhachHang getKhachHangByPhone(String phone) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from khachhang where SDT = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, phone);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            KhachHang kh = new KhachHang();
            
            kh.setKhachHangId(rs.getInt("KhachHangID"));
            kh.setHoDem(rs.getString("HoDem"));
            kh.setTen(rs.getString("Ten"));
            kh.setEmail(rs.getString("Email"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            
            return kh;
        }
        return null;
    }
    
    public boolean checkKhachHang(int taiKhoanId) throws SQLException{
         Connection conn = JdbcUtils.getConn();
         String sql = "select * from khachhang where TaiKhoanID = ?";
         PreparedStatement stm = conn.prepareStatement(sql);
         stm.setInt(1, taiKhoanId);
         ResultSet rs = stm.executeQuery();
         
         return rs.next();
    }
    
    public boolean updateKhachHang(KhachHang kh) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Update khachhang "
                + "set HoDem = ?, Ten = ?,Email = ?, SDT = ?, DiaChi = ?"
                + "where TaiKhoanID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kh.getHoDem());
        stm.setString(2, kh.getTen());
        stm.setString(3, kh.getEmail());
        stm.setString(4, kh.getSdt());
        stm.setString(5, kh.getDiaChi());
        stm.setInt(6, kh.getTaiKhoan().getTaiKhoanId());
        
        if (stm.executeUpdate() > 0)
        {
            conn.commit();
            return true;
        }
        
        return false;
    }
    
    public boolean deleteKhachHang(int khachHangId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Delete from khachhang where KhachHangID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, khachHangId);
        
        if(stm.executeUpdate() > 0)
        {
            conn.commit();
            return true;
        }
        
        return false;
    }
    
}
