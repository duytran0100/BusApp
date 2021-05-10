-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: busappdb
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banggia`
--

DROP TABLE IF EXISTS `banggia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banggia` (
  `BangGiaID` int NOT NULL AUTO_INCREMENT,
  `TuyenDuongID` int NOT NULL,
  `GiaTien` float NOT NULL,
  PRIMARY KEY (`BangGiaID`),
  KEY `fk_banggia_tuyenduong_idx` (`TuyenDuongID`),
  CONSTRAINT `fk_banggia_tuyenduong` FOREIGN KEY (`TuyenDuongID`) REFERENCES `tuyenduong` (`TuyenDuongID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banggia`
--

LOCK TABLES `banggia` WRITE;
/*!40000 ALTER TABLE `banggia` DISABLE KEYS */;
INSERT INTO `banggia` VALUES (1,1,100000),(2,2,100000),(3,3,150000),(4,4,250000),(5,5,250000);
/*!40000 ALTER TABLE `banggia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyenxe`
--

DROP TABLE IF EXISTS `chuyenxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyenxe` (
  `ChuyenXeID` int NOT NULL AUTO_INCREMENT,
  `GioKhoiHanh` time DEFAULT NULL,
  `XeID` int DEFAULT NULL,
  `GiaTien` float DEFAULT NULL,
  `TuyenDuongID` int DEFAULT NULL,
  `NgayKhoiHanh` date DEFAULT NULL,
  `SoVe` int DEFAULT NULL,
  PRIMARY KEY (`ChuyenXeID`),
  KEY `fk_chuyenxe_toid_xe_idx` (`XeID`),
  KEY `fk_chuyenxe_id_tuyenduong_idx` (`TuyenDuongID`),
  CONSTRAINT `fk_chuyenxe_id_tuyenduong` FOREIGN KEY (`TuyenDuongID`) REFERENCES `tuyenduong` (`TuyenDuongID`),
  CONSTRAINT `fk_chuyenxe_id_xe` FOREIGN KEY (`XeID`) REFERENCES `xe` (`XeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyenxe`
--

LOCK TABLES `chuyenxe` WRITE;
/*!40000 ALTER TABLE `chuyenxe` DISABLE KEYS */;
INSERT INTO `chuyenxe` VALUES (1,'07:30:00',1,100000,1,'2021-05-18',30),(2,'09:00:00',1,100000,2,'2021-05-18',30),(3,'09:00:00',2,150000,3,'2021-05-18',30),(4,'13:00:00',4,250000,5,'2021-05-18',25);
/*!40000 ALTER TABLE `chuyenxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `KhachHangID` int NOT NULL AUTO_INCREMENT,
  `HoDem` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Ten` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SDT` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TaiKhoanID` int DEFAULT NULL,
  PRIMARY KEY (`KhachHangID`),
  KEY `fk_khachhang_taikhoan_idx` (`TaiKhoanID`),
  CONSTRAINT `fk_khachhang_taikhoan` FOREIGN KEY (`TaiKhoanID`) REFERENCES `taikhoan` (`TaiKhoanID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Tran Quoc','Duy','duytran0001@gmail.com','0909792905','Tay Ninh',1),(4,'Tran Quoc','Duy','duytran0100@gmail.com','0909792908',NULL,NULL),(6,'Duy1','A',NULL,'12345677',NULL,NULL),(7,'AAAA','AA',NULL,'99933322',NULL,NULL),(8,'BBB','BBB',NULL,'99999222',NULL,NULL),(9,'LLLLL','LLLLL',NULL,'9999',NULL,NULL),(10,'LLLKKK','KKK',NULL,'6666666',NULL,NULL),(11,'Tran Quoc','Duy','duytran0100@gmail.com','0909792908',NULL,NULL);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `NhanVienID` int NOT NULL AUTO_INCREMENT,
  `HoDem` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ten` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SDT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TaiKhoanID` int NOT NULL,
  PRIMARY KEY (`NhanVienID`),
  KEY `fk_nhanvien_taikhoan_idx` (`TaiKhoanID`),
  CONSTRAINT `fk_nhanvien_taikhoan` FOREIGN KEY (`TaiKhoanID`) REFERENCES `taikhoan` (`TaiKhoanID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Tran','Duy','duytran0100@gmail.com','0886933092','Binh Duong',2);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `TaiKhoanID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiTaiKhoan` int NOT NULL,
  PRIMARY KEY (`TaiKhoanID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'kh1','25d55ad283aa400af464c76d713c07ad',0),(2,'nv1','25d55ad283aa400af464c76d713c07ad',1),(31,'kh2','25d55ad283aa400af464c76d713c07ad',0),(32,'nv2','25d55ad283aa400af464c76d713c07ad',1),(34,'duytran0100','e10adc3949ba59abbe56e057f20f883e',0);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tram`
--

DROP TABLE IF EXISTS `tram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tram` (
  `TramID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`TramID`),
  UNIQUE KEY `TramID_UNIQUE` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tram`
--

LOCK TABLES `tram` WRITE;
/*!40000 ALTER TABLE `tram` DISABLE KEYS */;
INSERT INTO `tram` VALUES (1,'An Sương','Củ Chi, TP Hồ Chí Minh'),(2,'Tân Châu','Huyện Tân Châu, Tây Ninh'),(3,'Bến xe Miền Tây','Bình Tân, TP Hồ Chí Minh'),(4,'Đồng Tháp','tx Đồng Tháp, Đồng Tháp');
/*!40000 ALTER TABLE `tram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuyenduong`
--

DROP TABLE IF EXISTS `tuyenduong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuyenduong` (
  `TuyenDuongID` int NOT NULL AUTO_INCREMENT,
  `TuyenDuongName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FromTram` int DEFAULT NULL,
  `ToTram` int DEFAULT NULL,
  `Distance` float DEFAULT NULL,
  `TuyenDuongTime` time DEFAULT NULL,
  PRIMARY KEY (`TuyenDuongID`),
  KEY `fk_tuyenduong_toid_tram_idx` (`FromTram`,`ToTram`),
  KEY `fk_tuyenduong_toid_tram_idx1` (`ToTram`),
  CONSTRAINT `fk_fromtram_toid_tram` FOREIGN KEY (`FromTram`) REFERENCES `tram` (`TramID`),
  CONSTRAINT `fk_totram_toid_tram` FOREIGN KEY (`ToTram`) REFERENCES `tram` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuyenduong`
--

LOCK TABLES `tuyenduong` WRITE;
/*!40000 ALTER TABLE `tuyenduong` DISABLE KEYS */;
INSERT INTO `tuyenduong` VALUES (1,'An Sương-Tân Châu',1,2,120,'02:30:00'),(2,'Tân Châu-An Sương',2,1,120,'02:30:00'),(3,'An Sương-Đồng Tháp',1,4,150,'03:07:00'),(4,'Tân Châu-Đồng Tháp',2,4,300,'06:15:00'),(5,'Đồng Tháp-Tân Châu',4,2,300,'06:15:00'),(6,'An Sương-Tân Châu',1,2,120,'02:30:00'),(7,'An Sương-Tân Châu',1,2,120,'02:30:00'),(8,'An Sương-Tân Châu',1,2,120,'02:30:00');
/*!40000 ALTER TABLE `tuyenduong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `VeXeID` int NOT NULL AUTO_INCREMENT,
  `ChuyenXeID` int DEFAULT NULL,
  `SoGhe` int DEFAULT NULL,
  `GioDat` time DEFAULT NULL,
  `NgayDat` date DEFAULT NULL,
  `KhachHangID` int DEFAULT NULL,
  `NhanVienID` int DEFAULT NULL,
  `VeDat` tinyint DEFAULT NULL,
  PRIMARY KEY (`VeXeID`),
  KEY `fk_vexe_id_chuyenxe_idx` (`ChuyenXeID`) /*!80000 INVISIBLE */,
  KEY `fk_vexe_khachhang_idx` (`KhachHangID`),
  KEY `fk_vexe_nhanvien_idx` (`NhanVienID`),
  CONSTRAINT `fk_vexe_id_chuyenxe` FOREIGN KEY (`ChuyenXeID`) REFERENCES `chuyenxe` (`ChuyenXeID`),
  CONSTRAINT `fk_vexe_khachhang` FOREIGN KEY (`KhachHangID`) REFERENCES `khachhang` (`KhachHangID`),
  CONSTRAINT `fk_vexe_nhanvien` FOREIGN KEY (`NhanVienID`) REFERENCES `nhanvien` (`NhanVienID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES (1,1,12,'21:36:49','2021-05-02',1,1,1),(15,1,19,'12:22:01','2021-05-09',6,NULL,1),(16,1,25,'12:22:15','2021-05-09',7,1,1),(18,1,20,'13:45:46','2021-05-09',9,1,1);
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xe`
--

DROP TABLE IF EXISTS `xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xe` (
  `XeID` int NOT NULL AUTO_INCREMENT,
  `BienSo` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `SoGhe` int NOT NULL,
  `LoaiXe` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `NamSX` date DEFAULT NULL,
  `TramID` int DEFAULT NULL,
  PRIMARY KEY (`XeID`),
  KEY `fk_xe_id_tram_idx` (`TramID`),
  CONSTRAINT `fk_xe_id_tram` FOREIGN KEY (`TramID`) REFERENCES `tram` (`TramID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xe`
--

LOCK TABLES `xe` WRITE;
/*!40000 ALTER TABLE `xe` DISABLE KEYS */;
INSERT INTO `xe` VALUES (1,'70k-1234',30,'Toyota','2015-05-15',2),(2,'59e-5592',30,'Honda','2017-05-17',1),(3,'59e-3456',30,'Honda','2017-05-10',3),(4,'66k-9988',25,'Toyota','2018-05-10',4);
/*!40000 ALTER TABLE `xe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 21:33:23
