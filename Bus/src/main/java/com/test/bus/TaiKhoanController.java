/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.KhachHang;
import com.test.pojo.NhanVien;
import com.test.pojo.TaiKhoan;
import com.test.service.KhachHangService;
import com.test.service.NhanVienService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class TaiKhoanController implements Initializable{
    
    @FXML TextField txtHoDem;
    @FXML TextField txtTen;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
    @FXML TextField txtSDT;
    
    KhachHangService khService;
    NhanVienService nvService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TaiKhoanData();
        } catch (Exception ex) {
            txtHoDem.setText("");
            txtTen.setText("");
            txtEmail.setText("");
            txtSDT.setText("");
            txtDiaChi.setText("");
        }
    }
    
    
    public void TaiKhoanData() throws SQLException, Exception{
        if(App.currentUser.getLoaiTaiKhoan() == 0)
        {
            khService = new KhachHangService();
            KhachHang kh = khService.getKhachHangByTaiKhoanID(App.currentUser.getTaiKhoanId());
            if(kh == null){
                throw new Exception();
            }
            txtHoDem.setText(kh.getHoDem());
            txtTen.setText(kh.getTen());
            txtDiaChi.setText(kh.getDiaChi());
            txtEmail.setText(kh.getEmail());
            txtSDT.setText(kh.getSdt());
        }
        else
        {
            nvService = new NhanVienService();
            NhanVien nv = nvService.getNhanVienByTaiKhoanID(App.currentUser.getTaiKhoanId());
            
            txtHoDem.setText(nv.getHoDem());
            txtTen.setText(nv.getTen());
            txtDiaChi.setText(nv.getDiaChi());
            txtEmail.setText(nv.getEmail());
            txtSDT.setText(nv.getSdt());
        }
    }
    
    public void updateThongTin(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thay đổi thông tin không thành công !!!");
        
        if(App.currentUser.getLoaiTaiKhoan() == 0){
            KhachHang kh = new KhachHang();
            kh.setHoDem(txtHoDem.getText());
            kh.setTen(txtTen.getText());
            kh.setDiaChi(txtDiaChi.getText());
            kh.setEmail(txtEmail.getText());
            kh.setSdt(txtSDT.getText());
            kh.setTaiKhoan(App.currentUser);
            khService = new KhachHangService();
            
            if(checkThongTin()){
                try {
                    khService.updateKhachHang(kh);
                    alert.setContentText("Thay đổi thông tin thành công");
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    khService.addKhachHang(kh, kh.getTaiKhoan().getTaiKhoanId());
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            NhanVien nv = new NhanVien();
            nv.setHoDem(txtHoDem.getText());
            nv.setTen(txtTen.getText());
            nv.setDiaChi(txtDiaChi.getText());
            nv.setEmail(txtEmail.getText());
            nv.setSdt(txtSDT.getText());
            nv.setTaiKhoan(App.currentUser);
            
            nvService = new NhanVienService();
            try {
                nvService.updateNhanVien(nv);
                alert.setContentText("Thay đổi thông tin thành công");
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        alert.show();
    }
    
    public boolean checkThongTin(){
        try {
            khService = new KhachHangService();
            boolean kq = khService.checkKhachHang(App.currentUser.getTaiKhoanId());
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void showChangePassword(){
        try {
            AnchorPane changePassword = FXMLLoader.load(getClass().getResource("doimatkhau.fxml"));
            Scene scene = new Scene(changePassword);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng đổi mật khẩu");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
