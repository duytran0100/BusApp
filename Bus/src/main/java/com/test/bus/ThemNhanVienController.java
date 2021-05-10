/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.NhanVien;
import com.test.pojo.TaiKhoan;
import com.test.service.NhanVienService;
import com.test.service.TaiKhoanService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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
    @FXML ChoiceBox cbTaiKhoan;
    public NhanVienService nhanVienService = new NhanVienService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCbTaiKhoan();
        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtSDT.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d{0,10}"))
                    txtSDT.setText(oldValue);
            }
        });
    }
    public void addNVHandler() throws SQLException {
        String hoDem = txtHo.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String emai = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        TaiKhoan tk = (TaiKhoan) cbTaiKhoan.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!hoDem.equals("") && !ten.equals("") && !sdt.equals("") && tk != null){
           if(nhanVienService.checkNhanVien(tk.getTaiKhoanId()))
           {
               alert.setContentText("Tài khoản này đã có người sở hữu");
           }
           else{
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTen(ten);
                nhanVien.setHoDem(hoDem);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setEmail(emai);
                nhanVien.setSdt(sdt);
                nhanVien.setTaiKhoan(tk);

                if(nhanVienService.addNhanVien(nhanVien)){
                    alert.setContentText("Thêm thành công");
                            clearHandler();
                }
                else{
                    alert.setContentText("Thêm thất bại");
                } 
           }
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin nhân viên");
        }
        alert.show();
            
        
    }
    
    public void loadCbTaiKhoan() throws SQLException{
        TaiKhoanService service = new TaiKhoanService();
        cbTaiKhoan.getItems().clear();
        cbTaiKhoan.setItems(FXCollections.observableArrayList(service.getTaiKhoan(1)));
    }
    
    public void clearHandler(){
        txtHo.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }
}
