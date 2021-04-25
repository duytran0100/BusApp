/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.Tram;
import com.test.pojo.Xe;
import com.test.service.TramService;
import com.test.service.XeService;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemXeController implements Initializable{
    @FXML TextField txtBienSo;
    @FXML TextField txtSoGhe;
    @FXML TextField txtLoaiXe;
    @FXML DatePicker dpNamSX;
    @FXML ChoiceBox<Tram> cbTram;
    public TramService tramService = new TramService();
    public XeService xeService = new XeService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbTram.setItems(FXCollections.observableArrayList(tramService.getTram("")));
            clearHandler();
        } catch (SQLException ex) {
            Logger.getLogger(ThemXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtSoGhe.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d{0,2}"))
                    txtSoGhe.setText(oldValue);
            }
        });
    }
    
    public void addXeHandler(){
        String bienSo = txtBienSo.getText();
        String soGhe = txtSoGhe.getText();
        String loaiXe = txtLoaiXe.getText();
        Date namSX = Date.valueOf(dpNamSX.getValue());
        Tram tram = cbTram.getValue();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!bienSo.equals("") && !soGhe.equals("")){
            try {
                //Kiểm tra biển số xe trong csdl
                if(xeService.checkBienSo(bienSo)){
                    alert.setContentText(String.format("Biển số xe %s đã tồn tại", bienSo));
                }
                //Nếu trong csdl chưa có biển số xe đó thì thêm xe
                else{
                    Xe xe = new Xe();
                    xe.setBienSo(bienSo);
                    xe.setSoGhe(Integer.parseInt(soGhe));
                    xe.setLoaiXe(loaiXe);
                    xe.setNamSX(namSX);
                    xe.setTram(tram);

                    if(xeService.addXe(xe)){
                        alert.setContentText("Thêm thành công");
                        clearHandler();
                    }
                    else{
                        alert.setContentText("Thêm thất bại");
                    } 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThemXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin xe");
        }
        alert.show();
    }
    
    public void clearHandler(){
        dpNamSX.setValue(LocalDate.now());
        txtBienSo.setText("");
        txtSoGhe.setText("");
        txtLoaiXe.setText("");
    }
}
