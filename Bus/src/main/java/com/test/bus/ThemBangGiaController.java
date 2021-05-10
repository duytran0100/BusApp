/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.BangGia;
import com.test.pojo.TuyenDuong;
import com.test.pojo.Xe;
import com.test.service.TuyenDuongService;
import com.test.service.BangGiaService;
import com.test.service.XeService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class ThemBangGiaController implements Initializable{
    @FXML ChoiceBox cbTuyenDuong;
    @FXML TextField txtGiaTien;
    
    BangGiaService bangGiaService;
    TuyenDuongService tuyenDuongService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            tuyenDuongService = new TuyenDuongService();
            cbTuyenDuong.setItems(FXCollections.observableArrayList(tuyenDuongService.getTuyenDuong("")));
        } catch (SQLException ex) {
            Logger.getLogger(ThemChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        txtGiaTien.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d{0,20}"))
                    txtGiaTien.setText(oldValue);
            }
        });
    }
    
    public void addBangGiaHandler() throws SQLException{
        String giaTien = txtGiaTien.getText();
        TuyenDuong td = (TuyenDuong) cbTuyenDuong.getValue();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!giaTien.equals("") && td != null){
            bangGiaService = new BangGiaService();
            if(bangGiaService.getBangGiaByTuyenDuongID(td.getTuyenDuongId()) != null)
            {
                alert.setContentText("Bảng giá đã tồn tại");
                
            }
            else
            {
                BangGia bangGia = new BangGia();
                bangGia.setGiaTien(Float.parseFloat(giaTien));
                bangGia.setTuyenDuong(td);

                if(bangGiaService.addBangGia(bangGia)){
                    alert.setContentText("Thêm thành công");
                    clearHandler();
                }
                else{
                    alert.setContentText("Thêm thất bại");
                } 
            }
        }
        else{
            alert.setContentText("Vui lòng điền đày đủ thông tin giá tiền");
        }
        alert.show();
            
        
    }
    
    public void clearHandler(){
        try {
            tuyenDuongService = new TuyenDuongService();
            txtGiaTien.setText("");
            cbTuyenDuong.getItems().clear();
            cbTuyenDuong.setItems(FXCollections.observableArrayList(tuyenDuongService.getTuyenDuong("")));
        } catch (SQLException ex) {
            Logger.getLogger(ThemBangGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
