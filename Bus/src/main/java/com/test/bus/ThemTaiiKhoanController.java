/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.TaiKhoan;
import com.test.service.TaiKhoanService;
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
public class ThemTaiiKhoanController implements Initializable{
    @FXML TextField txtUserName;
    @FXML TextField txtPassword;
    
    public TaiKhoanService taiKhoanService = new TaiKhoanService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void addTKHandler() throws SQLException{
        String userName = txtUserName.getText();
        String pass = txtPassword.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!userName.equals("") && !pass.equals("")){
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setUserName(userName);
            taiKhoan.setPassWord(pass);
            
            
            if(taiKhoanService.addTaiKhoan(userName, pass)){
                alert.setContentText("Thêm thành công");
                        clearHandler();
            }
            else{
                alert.setContentText("Thêm thất bại");
            } 
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin tài khoản");
        }
        alert.show();
            
        
    }
    
    public void clearHandler(){
        txtUserName.setText("");
        txtPassword.setText("");
    }
}
