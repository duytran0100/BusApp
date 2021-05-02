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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class ChangPasswordController implements Initializable{
    
    @FXML TextField txtOldPassWord;
    @FXML TextField txtNewPassWord;
    @FXML TextField txtConfirmPassWord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNewPassWord.setText("");
        txtOldPassWord.setText("");
        txtConfirmPassWord.setText("");
    }
    
    public void changePassword(ActionEvent e){
        String oldPass = txtOldPassWord.getText();
        String newPass = txtNewPassWord.getText();
        String confirmPass = txtConfirmPassWord.getText();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(oldPass.equals("") || newPass.equals("") || confirmPass.equals(""))
        {
            alert.setContentText("Vui lòng nhập mật khẩu !!!");
        }
        else
        {
            if(!App.currentUser.getPassWord().equals(DigestUtils.md5Hex(oldPass)))
            {
                alert.setContentText("Sai mật khẩu cũ !!!");
            }
            else if(!newPass.equals(confirmPass)){
                alert.setContentText("Mật khẩu xác nhận sai !!!");
            }
            else
            {
                TaiKhoanService tkService = new TaiKhoanService();
                
                try {
                    tkService.changePassword(App.currentUser.getUserName(), newPass);
                    
                    txtOldPassWord.setText("");
                    txtNewPassWord.setText("");
                    txtConfirmPassWord.setText("");
                    txtOldPassWord.requestFocus();
                    
                    alert.setContentText("Đổi mật khẩu thành công !!!");
                } catch (SQLException ex) {
                    Logger.getLogger(ChangPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        
        alert.show();
    }
    
    
}
