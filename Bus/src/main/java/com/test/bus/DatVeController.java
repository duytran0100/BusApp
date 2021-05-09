/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import java.net.URL;
import com.test.pojo.ChuyenXe;
import com.test.pojo.KhachHang;
import com.test.pojo.TuyenDuong;
import com.test.pojo.VeXe;
import com.test.service.ChuyenXeService;
import com.test.service.KhachHangService;
import com.test.service.VeXeService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Admin
 */
public class DatVeController implements Initializable{
    @FXML Label lbXe;
    @FXML Label lbTramDi;
    @FXML Label lbTramDen;
    @FXML Label lbGioKH;
    @FXML Label lbNgayKH;
    @FXML Label lbSoGhe;
    @FXML TextField txtHoDem;
    @FXML TextField txtTen;
    @FXML TextField txtSDT;
    @FXML VBox vbGhe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChuyenXe cx = TimVeController.getChuyenXe();
        TuyenDuong td = cx.getTuyenDuong();
         
        lbXe.setText(cx.getXe().toString());
        lbTramDi.setText(td.getTramDi().toString());
        lbTramDen.setText(td.getTramDen().toString());
        lbNgayKH.setText(cx.getNgayKhoiHanh().toString());
        lbGioKH.setText(cx.getGioKhoiHanh().toString());
        lbSoGhe.setText("");
        txtHoDem.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtSDT.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d{0,10}"))
                    txtSDT.setText(oldValue);
            }
        });
        
        
        try {
            loadGhe();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addHBox(HBox v){
        vbGhe.getChildren().add(v);
    }
    
    public void loadGhe() throws SQLException{
        vbGhe.getChildren().clear();
        ChuyenXe cx = TimVeController.getChuyenXe();
        int soVe = cx.getSoVe();
        int temp = 1;
        
        for(int i =0 ; i <= soVe / 5; i++)   
        {
            ArrayList <Button> buttons = new ArrayList <>();
            int count = 1;
            while(temp <= soVe && count <= 5){
                Button b = new Button(String.valueOf(temp));
                b.setPrefSize(50, 50);
                b.setOnAction((ActionEvent t) -> {
                    lbSoGhe.setText(b.getText());
                });
                
                VeXeService veService = new VeXeService();
                
                if(veService.kiemTraDat(temp, cx.getChuyenXeId())){
                    b.styleProperty().set("-fx-background-color:  gray;");
                    b.disableProperty().set(true);
                }
                else
                {
                    b.styleProperty().set("-fx-background-color:  #6495ED;");
                }
                        
                buttons.add(b);
                temp++;
                count++;
            }
            HBox h = new HBox();
            h.setPadding(new Insets(5));
            h.getChildren().addAll(buttons);
            addHBox(h);
        }
    }
    
    public void datVeHandler(){
        String soGhe = lbSoGhe.getText();
        String hoDem = txtHoDem.getText();
        String tenKH = txtTen.getText();
        String SDT = txtSDT.getText();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(soGhe.equals("")){
            alert.setContentText("Vui lòng chọn số ghế");
        }
        else if(hoDem.equals("") || tenKH.equals("") || SDT.equals("")){
            alert.setContentText("Vui lòng nhập thông tin khách hàng");
        }
        else{
            KhachHang kh = getKhachHang(hoDem, tenKH, SDT);
            boolean kq = datVe(Integer.parseInt(soGhe),kh);
            if(kq){
                alert.setContentText("Đặt vé thành công");
                try {
                    lbSoGhe.setText("");
                    txtHoDem.setText("");
                    txtTen.setText("");
                    txtSDT.setText("");
                    loadGhe();
                } catch (SQLException ex) {
                    Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                alert.setContentText("Đặt vé thất bại");
            }
        }
        
        alert.show();
    }
    
    private boolean datVe(int SoGhe,KhachHang kh){
        try {
            ChuyenXe cx = TimVeController.getChuyenXe();
            VeXeService veService = new VeXeService();
            if(veService.CheckTimeDatVe(cx.getChuyenXeId())){
                VeXe ve = new VeXe();
                ve.setChuyenXe(cx);
                ve.setSoGhe(SoGhe);
                ve.setKhachHang(kh);
                
                return veService.addVeXe(ve);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private KhachHang getKhachHang(String hoDem, String ten, String SDT){
        try {
            KhachHangService khService = new KhachHangService();
            KhachHang kh = khService.getKhachHangByPhone(SDT);
            
            if(kh == null){
                kh = new KhachHang();
                kh.setHoDem(hoDem);
                kh.setTen(ten);
                kh.setSdt(SDT);
                
                khService.addKhachHang(kh);
                
                return khService.getKhachHangByPhone(SDT);
            }
            else{
                return kh;
            }   
        } catch (SQLException ex) {
            Logger.getLogger(DatVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
