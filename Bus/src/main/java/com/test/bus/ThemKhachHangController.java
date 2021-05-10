/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;
import com.test.pojo.KhachHang;
import com.test.service.KhachHangService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class ThemKhachHangController implements Initializable{
    @FXML TextField txtHo;
    @FXML TextField txtTen;
    @FXML TextField txtSDT;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
    public KhachHangService khachHangService = new KhachHangService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearHandler();
        txtSDT.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d{0,10}"))
                    txtSDT.setText(oldValue);
            }
        });
    }
    public void addKhachHangHandler() throws SQLException{
        String hoDem = txtHo.getText();
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String emai = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(!ten.equals("") && !diaChi.equals("")){
            KhachHang khachHang = new KhachHang();
            khachHang.setTen(ten);
            khachHang.setHoDem(hoDem);
            khachHang.setDiaChi(diaChi);
            khachHang.setEmail(emai);
            khachHang.setSdt(sdt);
            
            if(khachHangService.addKhachHang(khachHang)){
                alert.setContentText("Thêm thành công");
                        clearHandler();
            }
            else{
                alert.setContentText("Thêm thất bại");
            } 
        }
        else{
            alert.setContentText("Vui lòng điền đầy đủ thông tin khách hàng");
        }
        alert.show();
            
        
    }
    
    public void clearHandler(){
        txtHo.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }

    
}