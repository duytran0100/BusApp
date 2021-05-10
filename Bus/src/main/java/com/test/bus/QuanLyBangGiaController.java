/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.BangGia;
import com.test.service.BangGiaService;
import com.test.service.TuyenDuongService;
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
 * @author Admin
 */
public class QuanLyBangGiaController implements Initializable{
    @FXML TextField txtBangGia;
    @FXML TableView<BangGia> tableBangGia;
    public BangGiaService bangGiaService;
    public TuyenDuongService tuyenDuongService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBangGiaTable();
        loadBangGiaData("");
        
        txtBangGia.textProperty().addListener(cl->{
            loadBangGiaData(txtBangGia.getText());
        });
    }
    
    public void loadBangGiaTable(){
        TableColumn colId = new TableColumn("Bảng Giá ID");
        colId.setCellValueFactory(new PropertyValueFactory("bangGiaId"));
        
        TableColumn colGiaTien = new TableColumn("Giá tiền");
        colGiaTien.setCellValueFactory(new PropertyValueFactory("giaTien"));
        
        TableColumn colName = new TableColumn("Tên tuyến đường");
        colName.setCellValueFactory(new PropertyValueFactory("tuyenDuong"));
        
        tableBangGia.getColumns().addAll(colId,colName,colGiaTien);
    }
    
    public void loadBangGiaData(String kw){
        try {
            bangGiaService = new BangGiaService();
            tableBangGia.getItems().clear();
            tableBangGia.setItems(FXCollections.observableArrayList(bangGiaService.getBangGia(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBangGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshDataHandler(){
        loadBangGiaData("");
    }
    
    public void showAddBangGia(){
        try {
            AnchorPane addBG = FXMLLoader.load(getClass().getResource("addbanggia.fxml"));
            Scene scene = new Scene(addBG);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm bảng giá");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadBangGiaData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyBangGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteBangGia(){
        BangGia td = tableBangGia.getSelectionModel().getSelectedItem();
        int tdId = td.getBangGiaId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chăc xóa bảng giá %s", td.getBangGiaId()));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            try {
                bangGiaService = new BangGiaService();
                alert.setAlertType(Alert.AlertType.INFORMATION);
                
                if(bangGiaService.deleteBangGia(tdId)){
                    alert.setContentText("Xóa thành công");
                    loadBangGiaData("");
                }
                else
                    alert.setContentText("Xóa thất bại\nDữ liệu bảng giá có thể đang được sử dụng");
                alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBangGiaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
