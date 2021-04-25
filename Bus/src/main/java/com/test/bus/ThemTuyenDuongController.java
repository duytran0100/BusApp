/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.Tram;
import com.test.pojo.TuyenDuong;
import com.test.service.TramService;
import com.test.service.TuyenDuongService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemTuyenDuongController implements Initializable{
    @FXML ChoiceBox<Tram> cbTramDen;
    @FXML ChoiceBox<Tram> cbTramDi;
    @FXML TextField txtKhoangCach;
    @FXML TextField txtThoiGian;
    TramService tramService;
    TuyenDuongService tdService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tramService = new TramService();
            cbTramDi.setItems(FXCollections.observableArrayList(tramService.getTram("")));
            cbTramDen.setItems(FXCollections.observableArrayList(tramService.getTram("")));
        } catch (SQLException ex) {
            Logger.getLogger(ThemTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtKhoangCach.textProperty().addListener(evt ->{
            double i = Double.parseDouble(txtKhoangCach.getText()) / 48;
            i = (double) Math.ceil(i * 1000) /1000;
            txtThoiGian.setText(String.valueOf(i));
        });
    }
    
    public void addTuyenDuongHandler(){
        String khoangCach = txtKhoangCach.getText();
        Tram tramDi = cbTramDi.getValue();
        Tram tramDen = cbTramDen.getValue();
        Alert alert = new Alert(AlertType.INFORMATION);
        
        if(!khoangCach.equals("") && tramDi != null && tramDen != null){  
            try {
                TuyenDuong td = new TuyenDuong();
                td.setTramDen(tramDen);
                td.setTramDi(tramDi);
                td.setKhoangCach(Float.parseFloat(khoangCach));
                tdService = new TuyenDuongService();
                
                if(tdService.addTuyenDuong(td)){
                    alert.setContentText("Thêm thành công");
                }
                else
                    alert.setContentText("Thêm thất bại");
            } 
            catch (SQLException ex) {
                Logger.getLogger(ThemTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
           alert.setContentText("Vui lòng điền đầy đủ thông tin tuyến đường");
        }
        alert.show();
    }
    
}
