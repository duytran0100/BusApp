/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.ChuyenXe;
import com.test.pojo.Tram;
import com.test.pojo.TuyenDuong;
import com.test.service.ChuyenXeService;
import com.test.service.TramService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class TimVeController implements Initializable{
    @FXML ChoiceBox<Tram> cbTramDi;
    @FXML ChoiceBox<Tram> cbTramDen;
    @FXML DatePicker dpNgayKhoiHanh;
    @FXML TableView tbChuyenXe;

    private static ChuyenXe chuyenXe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chuyenXe = new ChuyenXe();
        loadChuyenXeTable();
        try {
            loadTram();
        } catch (SQLException ex) {
            Logger.getLogger(TimVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        colId.prefWidthProperty().bind(tbChuyenXe.widthProperty().multiply(0.1));
        colTime.prefWidthProperty().bind(tbChuyenXe.widthProperty().multiply(0.15));
        colDate.prefWidthProperty().bind(tbChuyenXe.widthProperty().multiply(0.15));
        colXe.prefWidthProperty().bind(tbChuyenXe.widthProperty().multiply(0.15));
        colTuyenDuong.prefWidthProperty().bind(tbChuyenXe.widthProperty().multiply(0.15));
        
        
        tbChuyenXe.getColumns().addAll(colId,colTime,colDate,colXe,colTuyenDuong,colGiaTien,colSoVe);
    }
    
    void loadTram() throws SQLException{
        TramService tramService = new TramService();
        
        cbTramDi.setItems(FXCollections.observableArrayList(tramService.getTram("")));
        cbTramDen.setItems(FXCollections.observableArrayList(tramService.getTram("")));
    }
    
    public void TimVe(ActionEvent e){
        Tram tramDi = cbTramDi.getValue();
        Tram tramDen = cbTramDen.getValue();
        Date ngayKhoiHanh = Date.valueOf(dpNgayKhoiHanh.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(tramDi == null || tramDen == null || ngayKhoiHanh == null)
        {
            alert.setContentText("Vui lòng nhập đầy đủ thông tin ");
            alert.show();
        }
        else
        {
            ChuyenXeService cx = new ChuyenXeService();
            try {
                List<ChuyenXe> rs = cx.getChuyenXe(ngayKhoiHanh, tramDi.getTramId(), tramDen.getTramId());

                if(rs.isEmpty()){
                    alert.setContentText("Không tìm thấy chuyến xe");
                    alert.show();
                }
                else
                {
                    tbChuyenXe.setItems(FXCollections.observableArrayList(rs));
                }

            } catch (SQLException ex) {
                Logger.getLogger(TimVeController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
    
    public static ChuyenXe getChuyenXe(){
        return chuyenXe;
    }
    
    public void clickItem(MouseEvent e){
        if(e.getClickCount() == 2){
            chuyenXe = (ChuyenXe) tbChuyenXe.getSelectionModel().getSelectedItem();
            showDatVe();
        }
            
    }
    
    public void showDatVe(){
        try {
            AnchorPane Ve = FXMLLoader.load(getClass().getResource("datve.fxml"));
            Scene scene = new Scene(Ve);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng đặt vé");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(TimVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
