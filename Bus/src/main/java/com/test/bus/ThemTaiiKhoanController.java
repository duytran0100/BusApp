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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemTaiiKhoanController implements Initializable{
    @FXML TextField txtUserName;
    @FXML PasswordField txtPassword;
    @FXML TextField txtType;
    
    public TaiKhoanService taiKhoanService = new TaiKhoanService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void addTKHandler() throws SQLException{
        String userName = txtUserName.getText();
        String pass = txtPassword.getText();
        String type = txtType.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!userName.equals("") && !pass.equals("") && !type.equals("")){
            if(type.equals("1") || type.equals("0"))
            {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setUserName(userName);
                taiKhoan.setPassWord(pass);


                if(taiKhoanService.addTaiKhoan(userName, pass,Integer.parseInt(type))){
                    alert.setContentText("Thêm thành công");
                            clearHandler();
                }
                else{
                    alert.setContentText("Thêm thất bại");
                } 
            }
            else
            {
                alert.setContentText("Sai loại tài khoản, chỉ nhập 0 hoặc 1");
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
