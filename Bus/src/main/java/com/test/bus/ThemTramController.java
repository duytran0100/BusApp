/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.Tram;
import com.test.service.TramService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemTramController implements Initializable{
    @FXML TextField txtTenTram;
    @FXML TextField txtDiaChi;
    public TramService tramService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    public void addTramHandler(){
        String tenTram = txtTenTram.getText();
        String diaChi = txtDiaChi.getText();
        tramService = new TramService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!tenTram.equals("") && !diaChi.equals("")){
            Tram tram = new Tram();
            tram.setName(tenTram);
            tram.setDiaChi(diaChi);
                    
            if(tramService.addTram(tram)){
                alert.setContentText("Thêm trạm thành công");
                clearHandler();
            }
            else{
                alert.setContentText("Thêm trạm thất bại");
                clearHandler();
            } 
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin trạm");
        }
        alert.show();
    }
    
    public void clearHandler(){
        txtTenTram.setText("");
        txtDiaChi.setText("");
    }
}
