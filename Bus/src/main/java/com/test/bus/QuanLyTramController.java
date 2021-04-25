/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.Tram;
import com.test.service.TramService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
public class QuanLyTramController implements Initializable{
    @FXML TableView<Tram> tableTram;
    @FXML TextField txtTenTram;
    public TramService tramService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTramXe();
        loadTramData("");
        
        txtTenTram.textProperty().addListener(cl->{
            loadTramData(txtTenTram.getText());
        });
    }
    
    public void loadTramXe(){
        TableColumn colId = new TableColumn("Trạm ID");
        colId.setCellValueFactory(new PropertyValueFactory("tramId"));
        
        TableColumn colTen = new TableColumn("Tên Trạm");
        colTen.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn colDiaChi = new TableColumn("Địa Chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
        
        colTen.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.3));
        colId.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.3));
        colDiaChi.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.3));
        
        tableTram.getColumns().addAll(colId,colTen,colDiaChi);
    }
    
    public void loadTramData(String kw){
        try {
            tramService = new TramService();
            tableTram.getItems().clear();
            tableTram.setItems(FXCollections.observableArrayList(tramService.getTram(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshTramData(ActionEvent e){
        loadTramData("");
    }
    
    public void showAddTram(){
        try {
            AnchorPane addXe = FXMLLoader.load(getClass().getResource("addtram.fxml"));
            Scene scene = new Scene(addXe);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm trạm xe");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadTramData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTram(){
        Tram tram = tableTram.getSelectionModel().getSelectedItem();
        int idTram = tram.getTramId();
        String tenTram = tram.getName();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chắc muốn xóa trạm %s không?", tenTram));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            try {
                tramService = new TramService();
                alert.setAlertType(Alert.AlertType.INFORMATION);
                
                if(tramService.deleteTram(idTram)){
                    alert.setContentText("Xóa thành công");
                    loadTramData("");
                }
                else{
                    alert.setContentText("Xóa thất bại\nDữ liệu trạm có thể đang được sử dụng");
                }
                alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
