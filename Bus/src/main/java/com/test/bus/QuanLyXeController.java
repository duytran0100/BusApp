/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.Xe;
import com.test.service.XeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TableCell;
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
public class QuanLyXeController implements Initializable{
    @FXML TableView<Xe> tableXe;
    @FXML TextField txtBienSo;
    public XeService xeService = new XeService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadXe();
        loadData("");
        
        // Tìm kiếm theo biển số xe
        txtBienSo.textProperty().addListener(es ->{
            loadData(txtBienSo.getText());
        });
    }
    
    //Load table xe
    public void loadXe(){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        TableColumn colID = new TableColumn("Xe ID");
        colID.setCellValueFactory(new PropertyValueFactory("xeId"));
        
        TableColumn colBienSo = new TableColumn("Biển Số Xe");
        colBienSo.setCellValueFactory(new PropertyValueFactory("bienSo"));
        
        TableColumn colLoai = new TableColumn("Loại Xe");
        colLoai.setCellValueFactory(new PropertyValueFactory("loaiXe"));
        
        TableColumn colGhe = new TableColumn("Số Ghế");
        colGhe.setCellValueFactory(new PropertyValueFactory("soGhe"));
        
        TableColumn colNamSX = new TableColumn("Năm sản xuất");
        colNamSX.setCellValueFactory(new PropertyValueFactory("namSX"));
        
        colNamSX.setCellFactory(column ->{ 
            TableCell <Xe,Date> cell =new TableCell<Xe,Date>(){
                private final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                @Override
                public void updateItem(Date item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setText(null);
                    }
                    else
                        setText(f.format(item));
                }      
             };
            return cell;
        });
        
        TableColumn colTram = new TableColumn("Trạm Xe Đậu");
        colTram.setCellValueFactory(new PropertyValueFactory("tram"));
        
        colID.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.1));
        colBienSo.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.1));
        colLoai.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colNamSX.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colTram.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.4));
        tableXe.getColumns().addAll(colID,colBienSo,colLoai,colGhe,colNamSX,colTram);
    }
    
    //load data xe
    public void loadData(String kw){
        try {
            tableXe.getItems().clear();
            tableXe.setItems(FXCollections.observableArrayList(xeService.getXe(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //refresh data table
    public void refreshTableHandler() {
       loadData("");
    }
    
    //Hiển thị cửa sổ thêm xe
    public void showAddXe(){
        try {
            AnchorPane addXe = FXMLLoader.load(getClass().getResource("addxe.fxml"));
            Scene scene = new Scene(addXe);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm xe");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteXe(){
        Xe xe = tableXe.getSelectionModel().getSelectedItem();
        int idXe = xe.getXeId();
        String bienSo = xe.getBienSo();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chắc muốn xóa xe có biển số %s không?", bienSo));
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if(xeService.deleteXe(idXe))
            {
                alert.setContentText("Xóa thành công");
                loadData("");
            }
            else{
                alert.setContentText("Xóa thất bại\nDữ liệu xe có thể đang được sử dụng");
            }
            alert.show();
        }
    }
}
