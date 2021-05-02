/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service;

import com.test.pojo.NhanVien;
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
public class NhanVienService {
    public List<NhanVien> getNhanVien() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien";
        PreparedStatement stm =  conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<NhanVien> dsNhanVien = new ArrayList<>();
        while(rs.next()){
            NhanVien nv = new NhanVien();
            TaiKhoanService tkService = new TaiKhoanService();
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            nv.setTaiKhoan(tkService.getTaiKhoanById(rs.getInt("TaiKhoanID")));
            
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }
    
    public NhanVien getNhanVienById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where NhanVienID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            NhanVien nv = new NhanVien();
            
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            
            return nv;
        }
        return null;
    }
    
    public boolean addNhanVien(NhanVien nv) throws SQLException{
        // Kiểm tra xem có tồn tại thông tin trong csdl chưa
        if (this.checkNhanVien(nv.getTaiKhoan().getTaiKhoanId())) 
            return false;
        
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Insert into nhanvien(HoDem,Ten,Email,SDT,DiaChi,TaiKhoanID)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, nv.getHoDem());
        stm.setString(2, nv.getTen());
        stm.setString(3, nv.getEmail());
        stm.setString(4, nv.getSdt());
        stm.setString(5, nv.getDiaChi());
        stm.setInt(6,nv.getTaiKhoan().getTaiKhoanId());
        
        if (stm.executeUpdate() > 0){
            conn.commit();
            return true;
        }
        return false;
    }
    
    public List<NhanVien> getNhanVien(String name) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where Ten like concat('%',?,'%')";
        PreparedStatement stm =  conn.prepareStatement(sql);
        stm.setString(1,name);
        ResultSet rs = stm.executeQuery();
        List<NhanVien> dsNhanVien = new ArrayList<>();
        while(rs.next()){
            NhanVien nv = new NhanVien();
            TaiKhoanService tkService = new TaiKhoanService();
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            nv.setTaiKhoan(tkService.getTaiKhoanById(rs.getInt("TaiKhoanID")));
            
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }
    
    public NhanVien getNhanVienByPhone(String phone) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where SDT = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, phone);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            NhanVien nv = new NhanVien();
            
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            
            return nv;
        }
        return null;
    }
    
        public NhanVien getNhanVienByTaiKhoanID(int taiKhoanId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where TaiKhoanID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, taiKhoanId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            NhanVien nv = new NhanVien();
            
            nv.setNhanVienId(rs.getInt("NhanVienID"));
            nv.setHoDem(rs.getString("HoDem"));
            nv.setTen(rs.getString("Ten"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            
            return nv;
        }
        return null;
    }
    
    public boolean checkNhanVien(int taiKhoanId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "select * from nhanvien where TaiKhoanID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, taiKhoanId);
        ResultSet rs = stm.executeQuery();
         
        return rs.next();
    }
    
    public boolean updateNhanVien(NhanVien nv) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Update nhanvien "
                + "set HoDem = ?, Ten = ?,Email = ?, SDT = ?, DiaChi = ?"
                + "where TaiKhoanID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, nv.getHoDem());
        stm.setString(2, nv.getTen());
        stm.setString(3, nv.getEmail());
        stm.setString(4, nv.getSdt());
        stm.setString(5, nv.getDiaChi());
        stm.setInt(6, nv.getTaiKhoan().getTaiKhoanId());
        
        if (stm.executeUpdate() > 0)
        {
            conn.commit();
            return true;
        }
        
        return false;
    }
    
    public boolean deleteNhanVien(int nhanVienId) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "Delete from nhanvien where NhanVienID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, nhanVienId);
        
        if(stm.executeUpdate() > 0)
        {
            conn.commit();
            return true;
        }
        
        return false;
    }
}
