/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.service.KhachHangService;
import com.test.service.TramService;
import com.test.service.TuyenDuongService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemKhachHangController implements Initializable{
    @FXML TextField txtHo;
    @FXML TextField txtTen;
    @FXML TextField txtSDT;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
 
    KhachHangService khachHangService;
    
    public void addKhachHang(){
        String hoDem = txtHo.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String emai = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        
    }
    
    public void clearHandler(){
        txtHo.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
