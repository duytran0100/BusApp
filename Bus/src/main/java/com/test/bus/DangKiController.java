/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.service.TaiKhoanService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 *
 * @author Admin
 */
public class DangKiController implements Initializable{
    
    @FXML TextField txtUserName;
    @FXML TextField txtPassWord;
    @FXML TextField txtConfirmPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void DangKiHandler(ActionEvent e){
        String userName = txtUserName.getText();
        String passWord = txtPassWord.getText();
        String confirmPass = txtConfirmPass.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if (userName.equals("") || passWord.equals("") || confirmPass.equals("")){
            alert.setContentText("Vui lòng nhập thông tin tài khoản !!1");
        }
        else
        {
            try {
                TaiKhoanService tkService = new TaiKhoanService();
                
                if(tkService.checkUserName(userName)){
                    alert.setContentText("Tên tài khoản đã tồn tại !!!");
                }
                else if(!passWord.equals(confirmPass)){
                    alert.setContentText("Mật khẩu xác nhận không khớp !!!");
                }
                else
                {
                    tkService.addTaiKhoan(userName, passWord);
                    txtUserName.setText("");
                    txtPassWord.setText("");
                    txtConfirmPass.setText("");
                    
                    alert.setContentText("Đăng kí tài khoản thành công !!!");
                }
                    
            } catch (SQLException ex) {
                Logger.getLogger(DangKiController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        alert.show();
    }
    
    
}
