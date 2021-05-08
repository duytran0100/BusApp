/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.NhanVien;
import com.test.service.NhanVienService;
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
public class QuanLyNhanVienController implements Initializable{
    @FXML TableView<NhanVien> tableNhanVien;
    @FXML TextField txtNhanVien;
    public NhanVienService nhanVienService = new NhanVienService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStaff();
        loadData("");
        
        // Tìm kiếm theo tên tài khoản
        txtNhanVien.textProperty().addListener(es ->{
            loadData(txtNhanVien.getText());
        });
    }
    //load table Tai Khoan
    public void loadStaff(){
        TableColumn colID = new TableColumn("Nhân viên ID");
        colID.setCellValueFactory(new PropertyValueFactory("nhanVienId"));
        
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
        
        tableNhanVien.getColumns().addAll(colID,colHo,colTen,colSdt,colEmail,colDiaChi);
        
    }
    public void loadData(String kw) {
        try {
            tableNhanVien.getItems().clear();
            tableNhanVien.setItems(FXCollections.observableArrayList(nhanVienService.getNhanVien(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //refresh data table
    public void refreshTableHandler() {
       loadData("");
    }
    
    //Hiển thị cửa sổ thêm khách hàng
    public void showAddNV(){
        try {
            AnchorPane addNhanVien = FXMLLoader.load(getClass().getResource("addNhanVien.fxml"));
            Scene scene = new Scene(addNhanVien);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm nhân viên");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteNV() throws SQLException{
        NhanVien nhanVien = tableNhanVien.getSelectionModel().getSelectedItem();
        int idNV = nhanVien.getNhanVienId();
        String ten = nhanVien.getTen();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chắc muốn xóa nhân viên %s không?", ten));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if(nhanVienService.deleteNhanVien(idNV))
            {
                alert.setContentText("Xóa thành công");
                loadData("");
            }
            else{
                alert.setContentText("Xóa thất bại\nDữ liệu khách hàng có thể đang được sử dụng");
            }
            alert.show();
        }
    }
}
