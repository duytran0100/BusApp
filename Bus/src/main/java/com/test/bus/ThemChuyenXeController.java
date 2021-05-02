/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.TuyenDuong;
import com.test.pojo.Xe;
import com.test.pojo.ChuyenXe;
import com.test.service.BangGiaService;
import com.test.service.ChuyenXeService;
import com.test.service.TuyenDuongService;
import com.test.service.XeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;


/**
 *
 * @author Admin
 */
public class ThemChuyenXeController implements Initializable{
    @FXML Spinner hoursSpinner;
    @FXML Spinner minutesSpinner;
    @FXML ChoiceBox cbTuyenDuong;
    @FXML ChoiceBox cbXe;
    @FXML TextField txtSoVe;
    @FXML TextField txtGiaTien;
    @FXML DatePicker dpNgayKhoiHanh;
    TuyenDuongService tuyenDuongService;
    XeService xeService;
    BangGiaService bGiaService;
    ChuyenXeService cxService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initSpinner();
            tuyenDuongService = new TuyenDuongService();
            xeService = new XeService();
            cbTuyenDuong.setItems(FXCollections.observableArrayList(tuyenDuongService.getTuyenDuong("")));
            cbXe.setItems(FXCollections.observableArrayList(xeService.getXe("")));
            dpNgayKhoiHanh.setValue(LocalDate.now());
        } catch (SQLException ex) {
            Logger.getLogger(ThemChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cbTuyenDuong.getSelectionModel().selectedItemProperty().addListener(cl->{
            TuyenDuong td = (TuyenDuong) cbTuyenDuong.getSelectionModel().getSelectedItem();
            bGiaService = new BangGiaService();
            try {
                float giaTien = bGiaService.getBangGiaByTuyenDuongID(td.getTuyenDuongId()).getGiaTien();
                txtGiaTien.setText(String.valueOf(giaTien));
            } catch (SQLException ex) {
                Logger.getLogger(ThemChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        cbXe.getSelectionModel().selectedItemProperty().addListener(cl->{
            Xe xe = (Xe) cbXe.getSelectionModel().getSelectedItem();
            int soVe = xe.getSoGhe();
            txtSoVe.setText(String.valueOf(soVe));
        });

    }
    
    public void initSpinner(){
        SpinnerValueFactory<Integer> hoursValueFactory = 
               new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        
        SpinnerValueFactory<Integer> minutesValueFactory = //
               new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0);
        
        hoursSpinner.setValueFactory(hoursValueFactory);
        minutesSpinner.setValueFactory(minutesValueFactory);
    }
    
    public void ThemHandler(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            Xe xe = (Xe) cbXe.getValue();
            TuyenDuong td = (TuyenDuong) cbTuyenDuong.getValue();
            String hh = String.valueOf(hoursSpinner.getValue());
            String mm = String.valueOf(minutesSpinner.getValue());
            Date ngayKhoiHanh = Date.valueOf(dpNgayKhoiHanh.getValue());
            int soVe = Integer.parseInt(txtSoVe.getText());
            float giaTien = Float.parseFloat(txtGiaTien.getText());
            Time gioKhoiHanh = Time.valueOf(String.format("%s:%s:00", hh, mm));
            
            ChuyenXe chuyenXe = new ChuyenXe();
            chuyenXe.setTuyenDuong(td);
            chuyenXe.setXe(xe);
            chuyenXe.setGioKhoiHanh(gioKhoiHanh);
            chuyenXe.setNgayKhoiHanh(ngayKhoiHanh);
            chuyenXe.setGiaTien(giaTien);
            chuyenXe.setSoVe(soVe);
            
            cxService = new ChuyenXeService();
            if (cxService.addChuyenXe(chuyenXe))
                alert.setContentText("Thêm thành công");
            else
                alert.setContentText("Thêm thất bại");
            
        } catch (Exception ex) {
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
        }
        alert.show();
    }
    
}
