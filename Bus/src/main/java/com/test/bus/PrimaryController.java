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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrimaryController implements Initializable{
    @FXML BorderPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    public void loadLoginUI(ActionEvent e){
        try {
            loadUI("login.fxml");
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
