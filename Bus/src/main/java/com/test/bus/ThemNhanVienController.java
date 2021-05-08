/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.NhanVien;
import com.test.service.NhanVienService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemNhanVienController implements Initializable{

    @FXML TextField txtHo;
    @FXML TextField txtTen;
    @FXML TextField txtSDT;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
    public NhanVienService nhanVienService = new NhanVienService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void addNVHandler() throws SQLException{
        String hoDem = txtHo.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String emai = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!ten.equals("") && !diaChi.equals("")){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTen(ten);
            nhanVien.setHoDem(hoDem);
            nhanVien.setDiaChi(diaChi);
            nhanVien.setEmail(emai);
            nhanVien.setSdt(sdt);
            
            if(nhanVienService.addNhanVien(nhanVien)){
                alert.setContentText("Thêm thành công");
                        clearHandler();
            }
            else{
                alert.setContentText("Thêm thất bại");
            } 
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin nhân viên");
        }
        alert.show();
            
        
    }
    
    public void clearHandler(){
        txtHo.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }
}
