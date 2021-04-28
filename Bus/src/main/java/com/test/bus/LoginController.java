/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.TaiKhoan;
import com.test.service.TaiKhoanService;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class LoginController implements Initializable{
    
    @FXML TextField txtUserName;
    @FXML TextField txtPassWord;
    TaiKhoanService tkService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.currentUser = new TaiKhoan();
        txtUserName.setText("");
        txtPassWord.setText("");
        txtUserName.requestFocus();
    }
    
    public void Login(ActionEvent e){
        tkService = new TaiKhoanService();
        String userName = txtUserName.getText();
        String passWord = txtPassWord.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(userName.equals("") || passWord.equals("")){
            alert.setContentText("Vui lòng nhập tên tài khoản và mật khẩu");
        }
        else
        {
            try {
                if(tkService.checkLogin(userName, passWord)){
                    App.currentUser = tkService.getTaiKhoanByUserName(userName);
                    alert.setContentText("Đăng nhập thành công");
                    txtUserName.setText("");
                    txtPassWord.setText("");
                    
                    try {
                        App.setRoot("primary");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }//end if
                else{
                    alert.setContentText("Sai tên tài khoản hoặc mật khẩu");
                    txtUserName.setText("");
                    txtPassWord.setText("");
                    txtUserName.requestFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        alert.show();
    }
    
}
    
