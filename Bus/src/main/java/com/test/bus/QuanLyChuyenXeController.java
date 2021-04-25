/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.service.ChuyenXeService;
import com.test.pojo.TuyenDuong;
import com.test.pojo.ChuyenXe;
import com.test.service.TuyenDuongService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class QuanLyChuyenXeController implements Initializable{
    @FXML TableView<ChuyenXe> tableChuyenXe;
    @FXML DatePicker dpNgayKhoiHanh;
    @FXML ChoiceBox cbTuyenDuong;
    ChuyenXeService chuyenXeService;
    TuyenDuongService tuyenDuongService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadChuyenXeTable();
         loadChuyenXeData("");
         loadTuyenDuong();
    }
    
    public void loadChuyenXeTable(){
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("chuyenXeId"));
        
        TableColumn colTime = new TableColumn("Giờ khởi hành");
        colTime.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        
        TableColumn colDate = new TableColumn("Ngày khởi hành");
        colDate.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        
        colDate.setCellFactory(column ->{ 
            TableCell<ChuyenXe,Date> cell =new TableCell<ChuyenXe,Date>(){
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
        
        TableColumn colXe = new TableColumn("Xe");
        colXe.setCellValueFactory(new PropertyValueFactory("xe"));
        
        TableColumn colTuyenDuong = new TableColumn("Tuyến đường");
        colTuyenDuong.setCellValueFactory(new PropertyValueFactory("tuyenDuong"));
        
        colTuyenDuong.setCellFactory(column -> {
            TableCell <ChuyenXe, TuyenDuong> cell = new TableCell<>(){
                 
                 @Override
                 public void updateItem(TuyenDuong item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty )
                        setText(null);
                    else{
                        String result = item.getTuyenDuongName();
                        setText(result);
                        }
                }
            };
            return cell;
        });
        
        TableColumn colGiaTien = new TableColumn("Giá tiền");
        colGiaTien.setCellValueFactory(new PropertyValueFactory("giaTien"));
        
        TableColumn colSoVe = new TableColumn("Số Vé");
        colSoVe.setCellValueFactory(new PropertyValueFactory("soVe"));
        
        tableChuyenXe.getColumns().addAll(colId,colTime,colDate,colXe,colTuyenDuong,colGiaTien,colSoVe);
    }
    
    public void loadChuyenXeData(String kw){
        try {
            chuyenXeService = new ChuyenXeService();
            tableChuyenXe.getItems().clear();
            tableChuyenXe.setItems(FXCollections.observableArrayList(chuyenXeService.getChuyenXe()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadChuyenXeData(Date date, int tuyenDuongId){
        try {
            chuyenXeService = new ChuyenXeService();
            tableChuyenXe.getItems().clear();
            tableChuyenXe.setItems(FXCollections.observableArrayList(chuyenXeService.getChuyenXe(date, tuyenDuongId)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadTuyenDuong(){
        try {
            tuyenDuongService = new TuyenDuongService();
            cbTuyenDuong.getItems().clear();
            cbTuyenDuong.setItems(FXCollections.observableArrayList(tuyenDuongService.getTuyenDuong("")));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void TimChuyenXeHandler(ActionEvent e){
        try{
            Date date = Date.valueOf(dpNgayKhoiHanh.getValue());
            TuyenDuong td = (TuyenDuong) cbTuyenDuong.getValue();
            loadChuyenXeData(date, td.getTuyenDuongId());
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Vui lòng nhập thông tin tìm kiếm");
                alert.show();
        }
    }
    
    public void showAddChuyenXe(){
        try {
            AnchorPane addChuyenXe = FXMLLoader.load(getClass().getResource("addchuyenxe.fxml"));
            Scene scene = new Scene(addChuyenXe);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm chuyến xe");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            loadChuyenXeData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshChuyenXeData(){
        loadChuyenXeData("");
    }
    
    public void deleteChuyenXe(){
        ChuyenXe chuyenXe = tableChuyenXe.getSelectionModel().getSelectedItem();
        int idChuyenXe = chuyenXe.getChuyenXeId();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Bạn có chắc muốn xóa chuyến xe này không?");
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get() == ButtonType.OK){
            try {
                chuyenXeService = new ChuyenXeService();
                alert.setAlertType(Alert.AlertType.INFORMATION);
                
                if(chuyenXeService.deleteChuyenXe(idChuyenXe)){
                    alert.setContentText("Xóa thành công");
                    loadChuyenXeData("");
                }
                else{
                    alert.setContentText("Xóa thất bại\nDữ liệu chuyến xe có thể đang được sử dụng");
                }
                alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
