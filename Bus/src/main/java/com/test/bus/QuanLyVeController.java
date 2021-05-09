/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bus;

import com.test.pojo.KhachHang;
import com.test.pojo.NhanVien;
import com.test.pojo.VeXe;
import com.test.pojo.ChuyenXe;
import com.test.service.ChuyenXeService;
import com.test.service.VeXeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class QuanLyVeController implements Initializable{
    @FXML TableView tableVeXe;
    @FXML ChoiceBox cbChuyenXe;
    @FXML TextField txtSoGhe;
    public static VeXe veXe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadVeXeTable();
            LoadData();
            LoadCbChuyenXe();
            txtSoGhe.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadVeXeTable(){
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("veXeId"));
        
        TableColumn colChuyenXe = new TableColumn("Chuyến Xe");
        colChuyenXe.setCellValueFactory(new PropertyValueFactory("chuyenXe"));
        
        TableColumn colSoGhe = new TableColumn("Số Ghế");
        colSoGhe.setCellValueFactory(new PropertyValueFactory("soGhe"));
        
        TableColumn colGioDat = new TableColumn("Giờ Đặt");
        colGioDat.setCellValueFactory(new PropertyValueFactory("gioDat"));
        
        TableColumn colNgayDat = new TableColumn("Ngày Đặt");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        
        TableColumn colKhachHang = new TableColumn("Khách Hàng");
        colKhachHang.setCellValueFactory(new PropertyValueFactory("khachHang"));
        
        colKhachHang.setCellFactory(column -> {
            TableCell <VeXe, KhachHang> cell = new TableCell<>(){
                 
                 @Override
                 public void updateItem(KhachHang item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty )
                        setText(null);
                    else{
                        String result = item.getSdt();
                        setText(result);
                        }
                }
            };
            return cell;
        });
        
        TableColumn colNhanVien = new TableColumn("Nhân Viên");
        colNhanVien.setCellValueFactory(new PropertyValueFactory("nhanVien"));
        
        colNhanVien.setCellFactory(column -> {
            TableCell <VeXe, NhanVien> cell = new TableCell<>(){
                 
                 @Override
                 public void updateItem(NhanVien item,boolean empty){
                    super.updateItem(item,empty);
                    if(item == null || empty)
                        setText(null);
                    else{
                        String result = item.getSdt();
                        setText(result);
                        }
                }
            };
            return cell;
        });
        
        tableVeXe.getColumns().addAll(colID,colChuyenXe,colSoGhe,colGioDat,colNgayDat,colKhachHang,colNhanVien);
    }
    
    public void LoadData() throws SQLException{
        VeXeService veXeService = new VeXeService();
        
        tableVeXe.getItems().clear();
        tableVeXe.setItems(FXCollections.observableArrayList(veXeService.getVeXe()));
    }
    
    public void loadData(String soGhe, ChuyenXe cx) throws SQLException{
        VeXeService veXeService = new VeXeService();
        List<VeXe> dsVeXe = new ArrayList<>();
        if(soGhe.equals("")){
            dsVeXe = veXeService.getVeXeByChuyenXeId(cx.getChuyenXeId());
        }
        else{
            dsVeXe.add(veXeService.getVeXeBySoGhe(Integer.parseInt(soGhe), cx.getChuyenXeId()));
        }
        tableVeXe.getItems().clear();
        tableVeXe.setItems(FXCollections.observableArrayList(dsVeXe));
    }
    
    public void LoadCbChuyenXe() throws SQLException{
        ChuyenXeService cxService = new ChuyenXeService();
        cbChuyenXe.getItems().clear();
        cbChuyenXe.setItems(FXCollections.observableArrayList(cxService.getChuyenXe()));
    }
    
    public void TimVeHandler(ActionEvent e){
        String soGhe = txtSoGhe.getText();
        ChuyenXe cx = (ChuyenXe) cbChuyenXe.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(cx == null){
            alert.setContentText("Nhập thiếu thông tin tìm kiếm");
            alert.show();
        }
        else{
            try {
                loadData(soGhe, cx);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyVeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void HuyVeHandler(ActionEvent e){
        VeXe veXe = (VeXe) tableVeXe.getSelectionModel().getSelectedItem();
        VeXeService veXeService = new VeXeService();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("Bạn có chắc muốn xóa vé xe ghế %s này không?",veXe.getSoGhe()));
        
        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == ButtonType.OK){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if(veXeService.huyVeXe(veXe.getSoGhe(), veXe.getChuyenXe().getChuyenXeId()))
            {
                alert.setContentText("Xóa thành công");
                try {
                    LoadData();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                alert.setContentText("Xóa thất bại\nDữ liệu khách hàng có thể đang được sử dụng");
            }
            alert.show();
        }
        
    }
    
    public void showDoiVe(ActionEvent e){
        try {
            veXe = (VeXe) tableVeXe.getSelectionModel().getSelectedItem();
            
            AnchorPane doiVe = FXMLLoader.load(getClass().getResource("doive.fxml"));
            Scene scene = new Scene(doiVe);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng đổi vé");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            try {
                LoadData();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyVeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng chọn vé xe");
            alert.show();
        }
    }
    
    public void RefreshHandler(ActionEvent e){
        try {
            LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
