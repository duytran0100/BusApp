package com.test.bus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrimaryController implements Initializable{
    @FXML BorderPane rootPane;
    @FXML MenuButton btQuanLy;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(App.currentUser.getLoaiTaiKhoan() == 0){
            btQuanLy.disableProperty().set(true);
        }
    }   
    
    /**
     *
     *  Event power off
     * Exit APPLiCATION when Click power off
     * 
     */
    public void close (MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    
    public void loadUI(String UI) throws IOException{
       
        AnchorPane pane = FXMLLoader.load(getClass().getResource(UI));
        rootPane.setCenter(pane);
    }

    public void loadQuanLyXeUI(ActionEvent e){
        try {
            loadUI("quanlyxe.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadQuanLyTramUI(ActionEvent e){
        try {
            loadUI("quanlytram.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadQuanLyTuyenDuongUI(ActionEvent e){
        try {
            loadUI("quanlytuyenduong.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadQuanLyChuyenXeUI(ActionEvent e){
        try {
            loadUI("quanlychuyenxe.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadQuanLyKhachHangUI(ActionEvent e){
        try {
            loadUI("quanlykhachhang.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void loadQuanLyNhanVienUI(ActionEvent e){
        try {
            loadUI("quanlynhanvien.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadQuanLyTaiKhoanUI(ActionEvent e){
        try {
            loadUI("quanlytaikhoan.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadTaiKhoanUI(ActionEvent e){
        try {
            loadUI("thongtintaikhoan.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadQuanLyVeUI(ActionEvent e){
        try {
            loadUI("quanlyve.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadTimVeUI(ActionEvent e){
        try {
            loadUI("timve.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void logout(ActionEvent e){
        try {
            App.currentUser = null;
            App.setRoot("login");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
