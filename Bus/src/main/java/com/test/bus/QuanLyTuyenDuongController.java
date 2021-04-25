/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.TuyenDuong;
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
public class QuanLyTuyenDuongController implements Initializable{
    @FXML TextField txtTuyenDuong;
    @FXML TableView<TuyenDuong> tableTuyenDuong;
    public TuyenDuongService tuyenDuongService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTuyenDuongTable();
        loadTuyenDuongData("");
        
        txtTuyenDuong.textProperty().addListener(cl->{
            loadTuyenDuongData(txtTuyenDuong.getText());
        });
    }
    
    public void loadTuyenDuongTable(){
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("tuyenDuongId"));
        
        TableColumn colName = new TableColumn("Tên tuyến đường");
        colName.setCellValueFactory(new PropertyValueFactory("tuyenDuongName"));
        
        TableColumn colFromTram = new TableColumn("Trạm Đi");
        colFromTram.setCellValueFactory(new PropertyValueFactory("tramDi"));
        
        TableColumn colToTram = new TableColumn("Trạm Đến");
        colToTram.setCellValueFactory(new PropertyValueFactory("tramDen"));
        
        TableColumn colDistance = new TableColumn("Khoảng cách(KM)");
        colDistance.setCellValueFactory(new PropertyValueFactory("khoangCach"));
        
        TableColumn colTime = new TableColumn("Thời gian đến");
        colTime.setCellValueFactory(new PropertyValueFactory("thoiGianDuKien"));
        
        tableTuyenDuong.getColumns().addAll(colId,colName,colFromTram,colToTram,colDistance,colTime);
    }
    
    public void loadTuyenDuongData(String kw){
        try {
            tuyenDuongService = new TuyenDuongService();
            tableTuyenDuong.getItems().clear();
            tableTuyenDuong.setItems(FXCollections.observableArrayList(tuyenDuongService.getTuyenDuong(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshDataHandler(){
        loadTuyenDuongData("");
    }
    
    public void showAddTuyenDuong(){
        try {
            AnchorPane addXe = FXMLLoader.load(getClass().getResource("addtuyenduong.fxml"));
            Scene scene = new Scene(addXe);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm tuyến đường");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadTuyenDuongData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTuyenDuong(){
        TuyenDuong td = tableTuyenDuong.getSelectionModel().getSelectedItem();
        int tdId = td.getTuyenDuongId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chăc xóa tuyến %s", td.getTuyenDuongName()));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            try {
                tuyenDuongService = new TuyenDuongService();
                alert.setAlertType(Alert.AlertType.INFORMATION);
                
                if(tuyenDuongService.deleteTuyenDuong(tdId)){
                    alert.setContentText("Xóa thành công");
                    loadTuyenDuongData("");
                }
                else
                    alert.setContentText("Xóa thất bại\nDữ liệu tuyến đường có thể đang được sử dụng");
                alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
