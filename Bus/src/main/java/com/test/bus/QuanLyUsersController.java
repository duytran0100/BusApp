/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.KhachHang;
import com.test.service.KhachHangService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class QuanLyUsersController implements Initializable{
    @FXML TableView<KhachHang> tableUser;
    @FXML TextField txtTenKhachHang;
    public KhachHangService khachHangService = new KhachHangService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUser();
        loadData("");
        
        // Tìm kiếm theo tên khách hàng
        txtTenKhachHang.textProperty().addListener(es ->{
            loadData(txtTenKhachHang.getText());
        });
    }
    
    //load table Khách Hàng
    public void loadUser(){
        TableColumn colID = new TableColumn("Khách hàng ID");
        colID.setCellValueFactory(new PropertyValueFactory("khachHangId"));
        
        TableColumn colHo = new TableColumn("Họ đệm");
        colHo.setCellValueFactory(new PropertyValueFactory("hoDem"));
        
        TableColumn colTen = new TableColumn("Tên");
        colTen.setCellValueFactory(new PropertyValueFactory("ten"));
        
        TableColumn colSdt = new TableColumn("Phone");
        colSdt.setCellValueFactory(new PropertyValueFactory("sdt"));
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
        
        tableUser.getColumns().addAll(colID,colHo,colTen,colSdt,colEmail,colDiaChi);
        
    }
    
    //load data Khách Hàng
    public void loadData(String kw) {
        try {
            tableUser.getItems().clear();
            tableUser.setItems(FXCollections.observableArrayList(khachHangService.getKhachHang(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //refresh data table
    public void refreshTableHandler() {
       loadData("");
    }
    
    //Hiển thị cửa sổ thêm khách hàng
    public void addKhachHang(){
        try {
            AnchorPane addKhachHang = FXMLLoader.load(getClass().getResource("addkhachhang.fxml"));
            Scene scene = new Scene(addKhachHang);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm khách hàng");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
