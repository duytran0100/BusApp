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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    @FXML Button btLogin;
    @FXML TextField txtTimer;
            
    TaiKhoanService tkService;
    private int count;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.currentUser = new TaiKhoan();
        txtUserName.setText("");
        txtPassWord.setText("");
        txtUserName.requestFocus();
        txtTimer.visibleProperty().set(false);
        count = 4;
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
                    
                    try {
                        App.setRoot("primary");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }//end if
                else{
                    alert.setContentText("Sai tên tài khoản hoặc mật khẩu");
                    
                    txtPassWord.setText("");
                    txtUserName.requestFocus();
                    
                    count -= 1;
                    
                    if (count == 0){
                        alert.setContentText("Bạn đã bị khóa đăng nhập trong 240 giây");
                        btLogin.disableProperty().set(true);
                        txtTimer.visibleProperty().set(true);
                        Countdown();
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        alert.show();
        
    }
    
    private void Countdown(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int seconds = 240;
            @Override
            public void run() {
                txtTimer.setText(String.valueOf(seconds));
                
                if (seconds == 0){
                    timer.cancel();
                    txtTimer.visibleProperty().set(false);
                    btLogin.disableProperty().set(false);
                }
                
                seconds -=1;
            }
        },0,1000);
    }
    
    public void showDangKi(ActionEvent e){
        try {
            AnchorPane dangki = FXMLLoader.load(getClass().getResource("dangki.fxml"));
            Scene scene = new Scene(dangki);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng đăng kí");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
    
