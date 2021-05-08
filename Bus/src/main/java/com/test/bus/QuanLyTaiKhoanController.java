/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.TaiKhoan;
import com.test.service.TaiKhoanService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
 * @author DELL
 */
public class QuanLyTaiKhoanController implements Initializable{
    @FXML TableView<TaiKhoan> tableAccount;
    @FXML TextField txtTaiKhoan;
    public TaiKhoanService taiKhoanService = new TaiKhoanService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAccount();
        loadData("");
        
        // Tìm kiếm theo tên tài khoản
        txtTaiKhoan.textProperty().addListener(es ->{
            loadData(txtTaiKhoan.getText());
        });
    }
    //load table Tai Khoan
    public void loadAccount(){
        TableColumn colID = new TableColumn("Tài khoản ID");
        colID.setCellValueFactory(new PropertyValueFactory("taiKhoanId"));
        
        TableColumn colName = new TableColumn("User Name");
        colName.setCellValueFactory(new PropertyValueFactory("userName"));
        
        TableColumn colPass = new TableColumn("Password");
        colPass.setCellValueFactory(new PropertyValueFactory("passWord"));
        
        TableColumn colTypeAcc = new TableColumn("Loại tài khoản");
        colTypeAcc.setCellValueFactory(new PropertyValueFactory("loaiTaiKhoan"));
        
        tableAccount.getColumns().addAll(colID,colName,colPass,colTypeAcc);
        
    }
    public void loadData(String kw) {
        try {
            tableAccount.getItems().clear();
            tableAccount.setItems(FXCollections.observableArrayList(taiKhoanService.getTaiKhoan(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //refresh data table
    public void refreshTableHandler() {
       loadData("");
    }
    
    public void showAddTaiKhoan(){
        try {
            AnchorPane addTaiKhoan = FXMLLoader.load(getClass().getResource("addtaikhoan.fxml"));
            Scene scene = new Scene(addTaiKhoan);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm tài khoản");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deteleTaiKhoan() throws SQLException{
        TaiKhoan taiKhoan = tableAccount.getSelectionModel().getSelectedItem();
        int idTK = taiKhoan.getTaiKhoanId();
        String ten = taiKhoan.getUserName();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chắc muốn xóa tài khoản %s không?", ten));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if(taiKhoanService.deleteTaiKhoan(idTK))
            {
                alert.setContentText("Xóa thành công");
                loadData("");
            }
            else{
                alert.setContentText("Xóa thất bại\nDữ liệu tài khoản có thể đang được sử dụng");
            }
            alert.show();
        }
    }
}
