/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.VeXe;
import com.test.pojo.ChuyenXe;
import com.test.pojo.NhanVien;
import com.test.service.ChuyenXeService;
import com.test.service.NhanVienService;
import com.test.service.VeXeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DoiVeController implements Initializable{
    @FXML ChoiceBox cbNewChuyenXe;
    @FXML TextField txtOldGhe;
    @FXML TextField txtNewGhe;
    @FXML TextField txtOldChuyenXe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VeXe veXe = QuanLyVeController.veXe;
        
        txtOldChuyenXe.setText(veXe.getChuyenXe().toString());
        txtOldGhe.setText(String.valueOf(veXe.getSoGhe()));
        txtNewGhe.setText("");
        LoadChuyenXe();
    }
    
    public void LoadChuyenXe(){
        try {
            ChuyenXeService cxService = new ChuyenXeService();
            
            cbNewChuyenXe.getItems().clear();
            cbNewChuyenXe.setItems(FXCollections.observableArrayList(cxService.getChuyenXe()));
        } catch (SQLException ex) {
            Logger.getLogger(DoiVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DoiVeHandler(){
        try {
            VeXe veCu = QuanLyVeController.veXe;
            ChuyenXe cx = (ChuyenXe) cbNewChuyenXe.getSelectionModel().getSelectedItem();
            String soGhe = txtNewGhe.getText();
            VeXeService veXeService = new VeXeService();
            NhanVienService nvService = new NhanVienService();
            NhanVien nv = nvService.getNhanVienByTaiKhoanID(App.currentUser.getTaiKhoanId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            if(cx == null && soGhe.equals("")){
                alert.setContentText("Vui lòng nhập thông tin vé mới");
            }
            else{
                if(veXeService.CheckTimeDatVe(cx.getChuyenXeId())){
                    if(veXeService.doiVe(veCu,Integer.parseInt(soGhe)
                            ,cx.getChuyenXeId(),nv.getNhanVienId()))
                    {
                        alert.setContentText("Đổi vé thành công");
                        RefreshHandler();
                    }
                    else{
                        alert.setContentText("Đổi vé thất bại");
                    }
                }
                else{
                    alert.setContentText("Hết thời gian đặt vé");
                }
            }
            alert.show();
        } catch (SQLException ex) {
            Logger.getLogger(DoiVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void RefreshHandler(){
        LoadChuyenXe();
        txtNewGhe.setText("");
    }
}
