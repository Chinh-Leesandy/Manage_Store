-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: manager_store
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `brand_id` varchar(255) NOT NULL,
  `brand_email` varchar(255) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES ('brand10',NULL,'MEO STORE'),('brand2','xita@gmail.com','XITA'),('brand3','hoavi@gmail.com','Áo Dài Hòa Vi'),('brand4','','Ayserkris'),('brand5',NULL,'Angeletta'),('brand6',NULL,'LAVIICI'),('brand7',NULL,'CICI'),('brand8',NULL,'LALLEE'),('brand9',NULL,'TRIO JI');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` varchar(255) NOT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK_867x3yysb1f3jk41cv3vsoejj` (`customer_id`),
  CONSTRAINT `FKdebwvad6pp1ekiqy5jtixqbaj` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('979d0903-1c1d-4be9-a9cc-51593068a34b-1712832550218','customer1'),('a0ae4e1d-9e17-418b-ab7e-6adf7a10760b-1714982370522','customer2'),('a75d79b8-d050-4ba8-a6d7-d09b99f561ff-1713245346769','customer3'),('1e8dbe6e-14bb-41de-bf49-a6d4fd71bcbe-1713595867214','customer4'),('579905f9-64d1-4263-82c0-d3f4fc6a9227-1715061649846','customer5');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_product`
--

DROP TABLE IF EXISTS `cart_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_product` (
  `cart_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `amount` int DEFAULT NULL,
  KEY `FK2kdlr8hs2bwl14u8oop49vrxi` (`product_id`),
  KEY `FKlv5x4iresnv4xspvomrwd8ej9` (`cart_id`),
  CONSTRAINT `FK2kdlr8hs2bwl14u8oop49vrxi` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKlv5x4iresnv4xspvomrwd8ej9` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_product`
--

LOCK TABLES `cart_product` WRITE;
/*!40000 ALTER TABLE `cart_product` DISABLE KEYS */;
INSERT INTO `cart_product` VALUES ('979d0903-1c1d-4be9-a9cc-51593068a34b-1712832550218','a94953aa-61b9-4614-b063-6e1227ca3997-1713163768413',1);
/*!40000 ALTER TABLE `cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` varchar(255) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('cate1','Đầm'),('cate2','Áo dài'),('cate3','Áo'),('cate4','Áo khoác'),('cate5','Quần'),('cate6','Chân váy'),('cate7','Đồ liền thân'),('cate8','Đồ bơi'),('cate9','vest');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chamcong`
--

DROP TABLE IF EXISTS `chamcong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chamcong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idnhanvien` int NOT NULL,
  `thoigianvao` timestamp NOT NULL,
  `thoigianra` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nhanvien_idx` (`idnhanvien`),
  CONSTRAINT `idx_nhanvien` FOREIGN KEY (`idnhanvien`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamcong`
--

LOCK TABLES `chamcong` WRITE;
/*!40000 ALTER TABLE `chamcong` DISABLE KEYS */;
INSERT INTO `chamcong` VALUES (1,1,'2024-03-10 01:30:00','2024-03-10 10:45:00'),(2,2,'2024-03-20 02:15:00','2024-03-20 11:00:00'),(3,1,'2024-03-25 03:00:00','2024-03-25 09:30:00');
/*!40000 ALTER TABLE `chamcong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('customer1','hiep','hiep','1234','hiep'),('customer2','c','c','1245','c'),('customer3','h','h','093425','h'),('customer4','a','a','3255','a'),('customer5','b','b','9805','b');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` varchar(255) NOT NULL,
  `total_amount` int DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `payment_id` varchar(255) DEFAULT NULL,
  `shipment_id` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `FK5e32ukwo9uknwhylogvta4po6` (`customer_id`),
  KEY `FKbaxa82hce5x7dqj0sotnc1cxf` (`payment_id`),
  KEY `FKp0wjajquhluu15w4iuau7jx9p` (`shipment_id`),
  CONSTRAINT `FK5e32ukwo9uknwhylogvta4po6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKbaxa82hce5x7dqj0sotnc1cxf` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  CONSTRAINT `FKp0wjajquhluu15w4iuau7jx9p` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`shipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('22748421-c82b-4d52-a9ac-e2373aa0e230-1715063482935',5,3418000,'customer1','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2023-12-07','Hà Giang'),('32b8d7a2-b653-404a-b4bd-024c4451cce9-1715048726076',3,2736000,'customer4','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2024-05-07','HN'),('3a75d7c4-26be-4629-bbc6-4cf42eeae175-1715062422881',1,1420000,'customer5','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2024-05-07','Hòa Bình'),('62839146-dc07-4c7f-a243-640fda3aee16-1715062060441',3,2991000,'customer5','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350','2023-03-07','Thanh Hóa'),('71123034-22ca-4143-9a8c-7ef3e491dbb5-1715062642463',2,3025000,'customer2','e5fd9f1d-bd09-4b5e-bcaf-5e7fb02b8c66-1712893034149','53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350','2024-06-16','Ninh Bình'),('72c45348-f313-4abc-bd33-32ef95909c1b-1715065310316',2,2075000,'customer1','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350','2024-05-07','ABcd'),('9026bd45-f9c2-4c13-8368-09bda7771aa2-1715061687306',5,6673000,'customer5','e5fd9f1d-bd09-4b5e-bcaf-5e7fb02b8c66-1712893034149','53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350','2024-05-07','Điện Biên'),('a711c610-2e10-4cf8-ba5c-c8b8c0d7f021-1715062888139',3,2880000,'customer2','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2024-02-07','Hà Nam'),('b14413da-9c0a-4198-82f8-e5960418c6ca-1715050818400',3,4080000,'customer4','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2023-10-07','weg'),('b795b476-dacc-4ad9-aa67-fd18973b8edf-1715066829200',3,3865000,'customer1','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350','2024-05-07','Bắc Nình'),('cecc5e86-6e5c-4751-b2ec-de91009d75b8-1715049669164',1,510000,'customer4','1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490','2024-05-07','Bắc Ninh');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_product`
--

DROP TABLE IF EXISTS `invoice_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_product` (
  `invoice_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `amount` int DEFAULT NULL,
  `total_price` double DEFAULT '0',
  PRIMARY KEY (`invoice_id`,`product_id`),
  KEY `FK806bu27uepq9jw1gksvegoqkd` (`product_id`),
  CONSTRAINT `FK806bu27uepq9jw1gksvegoqkd` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKhrqne4uostar9vds76ynsosov` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_product`
--

LOCK TABLES `invoice_product` WRITE;
/*!40000 ALTER TABLE `invoice_product` DISABLE KEYS */;
INSERT INTO `invoice_product` VALUES ('22748421-c82b-4d52-a9ac-e2373aa0e230-1715063482935','140d9d97-abfb-4b25-917f-3290e2f81162-1713163768409',2,1398000),('22748421-c82b-4d52-a9ac-e2373aa0e230-1715063482935','66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422',2,600000),('22748421-c82b-4d52-a9ac-e2373aa0e230-1715063482935','95285f98-4813-4ae3-85ec-b89c6a7e811a-1713163768429',1,1400000),('32b8d7a2-b653-404a-b4bd-024c4451cce9-1715048726076','16eac33f-c447-4ae7-bc5e-3e9db1de55bd-1713163768426',2,1216000),('32b8d7a2-b653-404a-b4bd-024c4451cce9-1715048726076','def4c8fd-dfd9-4417-855f-f24e2c58141e-1713163768434',1,1500000),('3a75d7c4-26be-4629-bbc6-4cf42eeae175-1715062422881','95285f98-4813-4ae3-85ec-b89c6a7e811a-1713163768429',1,1400000),('62839146-dc07-4c7f-a243-640fda3aee16-1715062060441','16eac33f-c447-4ae7-bc5e-3e9db1de55bd-1713163768426',2,1216000),('62839146-dc07-4c7f-a243-640fda3aee16-1715062060441','c022c43b-c9fc-4669-8982-79f35fc37914-1713163768439',1,1750000),('71123034-22ca-4143-9a8c-7ef3e491dbb5-1715062642463','def4c8fd-dfd9-4417-855f-f24e2c58141e-1713163768434',2,3000000),('72c45348-f313-4abc-bd33-32ef95909c1b-1715065310316','66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422',1,300000),('72c45348-f313-4abc-bd33-32ef95909c1b-1715065310316','c022c43b-c9fc-4669-8982-79f35fc37914-1713163768439',1,1750000),('9026bd45-f9c2-4c13-8368-09bda7771aa2-1715061687306','140d9d97-abfb-4b25-917f-3290e2f81162-1713163768409',2,1398000),('9026bd45-f9c2-4c13-8368-09bda7771aa2-1715061687306','c022c43b-c9fc-4669-8982-79f35fc37914-1713163768439',3,5250000),('a711c610-2e10-4cf8-ba5c-c8b8c0d7f021-1715062888139','66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422',1,300000),('a711c610-2e10-4cf8-ba5c-c8b8c0d7f021-1715062888139','f731ecad-71cd-43b6-810d-a724c3c58aa4-1713163768418',2,2560000),('b14413da-9c0a-4198-82f8-e5960418c6ca-1715050818400','def4c8fd-dfd9-4417-855f-f24e2c58141e-1713163768434',1,1500000),('b14413da-9c0a-4198-82f8-e5960418c6ca-1715050818400','f731ecad-71cd-43b6-810d-a724c3c58aa4-1713163768418',2,2560000),('b795b476-dacc-4ad9-aa67-fd18973b8edf-1715066829200','f731ecad-71cd-43b6-810d-a724c3c58aa4-1713163768418',3,3840000),('cecc5e86-6e5c-4751-b2ec-de91009d75b8-1715049669164','645f1d79-179f-45e9-a7b0-771893b98fda-1713163768443',1,490000);
/*!40000 ALTER TABLE `invoice_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaimathang`
--

DROP TABLE IF EXISTS `loaimathang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaimathang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) NOT NULL,
  `nhacungcap` varchar(45) NOT NULL,
  `thoigiannhap` date NOT NULL,
  `soluong` int NOT NULL,
  `vitritrungbay` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaimathang`
--

LOCK TABLES `loaimathang` WRITE;
/*!40000 ALTER TABLE `loaimathang` DISABLE KEYS */;
INSERT INTO `loaimathang` VALUES (1,'Đầm','Quảng Châu','2024-04-02',3,'Kệ số 2 - Hàng số 3'),(2,'Vest','Việt Tiến','2024-03-15',10,'Kệ số 2 - Hàng số 1');
/*!40000 ALTER TABLE `loaimathang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nghiphep`
--

DROP TABLE IF EXISTS `nghiphep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nghiphep` (
  `idnghiphep` int NOT NULL AUTO_INCREMENT,
  `idnhanvien` int NOT NULL,
  `thoigian` date NOT NULL,
  `songaynghi` int NOT NULL,
  PRIMARY KEY (`idnghiphep`),
  KEY `idx_idnhanvien1_idx` (`idnhanvien`),
  CONSTRAINT `idx_idnhanvien1` FOREIGN KEY (`idnhanvien`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nghiphep`
--

LOCK TABLES `nghiphep` WRITE;
/*!40000 ALTER TABLE `nghiphep` DISABLE KEYS */;
INSERT INTO `nghiphep` VALUES (1,1,'2024-03-01',2),(2,2,'2024-04-01',3),(4,1,'2024-04-08',1);
/*!40000 ALTER TABLE `nghiphep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(45) NOT NULL,
  `sdt` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `diachi` varchar(45) NOT NULL,
  `chucvu` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ngaybatdaulv` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Nguyễn Chinh','0948266581','chinh@gmail.com','Thanh Trì - Hà Nội','Quản lý loại hàng','Chinh','Chinh@123','2023-03-01'),(2,'Nguyễn Cường','0315914471','cuonng@gmail.com','Hà Đông - Hà Nội','Quản lý nhân viên','Cuong','Cuong@234','2023-04-01');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` varchar(255) NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('1856d830-d3b0-498e-88ea-f21cc6886b29-1712893041676','Bank'),('e5fd9f1d-bd09-4b5e-bcaf-5e7fb02b8c66-1712893034149','Cash');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(255) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `brand_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_brand_idx` (`brand_id`),
  CONSTRAINT `fk_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('140d9d97-abfb-4b25-917f-3290e2f81162-1713163768409','Đầm Ngắn Hai Dây Dài Tay',699000,'brand7','Cam kết:  • Sản phẩm chính hãng. • Sản phẩm “mới hoặc như mới” nhờ công nghệ giặt hấp cao cấp.','https://product.hstatic.net/200000456167/product/cici_19_-_1_8807d5f33bec43e88e23ae80f3f3e2d5_master.jpg',16),('16eac33f-c447-4ae7-bc5e-3e9db1de55bd-1713163768426','Áo Blazer Ngắn x Chân Váy',608000,'brand9','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/323628565_711552373879101_6473884468540865776_n_bdaa6b04b71044d58061b51d98eec69e_master.jpg',6),('645f1d79-179f-45e9-a7b0-771893b98fda-1713163768443','Áo Vest Trễ Vai x Chân Váy Midi Voan Mỏng',490000,'brand10','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/meo_store_18_-_1_282f5731e61c4040845c63977d7d034e_master.jpg',8),('66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422','Đồ Bơi Lệch Vai Họa Tiết',300000,'brand2','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/xita_bb6bea9a3b6a4fffbb97e5d7ad7e298b_master.jpg',13),('7abfdef1-4786-47e2-b56c-d3fc3c855a58-1713163768451','Áo Vest Cổ Sơ Mi Tay Dài x Quần Dài Dáng Suông',1500000,'brand8','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/lallee_02_-_1_cb84525e945245a489f6c8b0c73ff252_master.jpg',6),('95285f98-4813-4ae3-85ec-b89c6a7e811a-1713163768429','Áo Dài Kiểu Voan Mỏng',1400000,'brand3','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/hoa_vi_2c122473988b4c64a3474ef29c62d169_master.jpg',7),('a94953aa-61b9-4614-b063-6e1227ca3997-1713163768413','Đầm Ngắn Hai Dây Tay Dài',1100000,'brand7','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/cici_18_-_1_4751bb4bfeaf4a27a55302526cc5ce13_master.jpg',10),('c022c43b-c9fc-4669-8982-79f35fc37914-1713163768439','Đầm Vest Tay Dài Đính Hoa',1750000,'brand5','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/angeletta_04_-_1_832746d93872477d8bebaf02ed68c114_master.jpg',7),('c6006a3b-5702-4767-a796-3e6e18899057-1713163768447','Áo Vest Tay Dài x Quần Dài Dáng Suông',1390000,'brand8','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/lallee_03_-_1_d0591691e1a0475bb7f456f0568740fc_master.jpg',5),('def4c8fd-dfd9-4417-855f-f24e2c58141e-1713163768434','Áo Dài Vai Kiểu Đính Hoa',1500000,'brand3','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/hoa_vi_80e5e95bda8245e7a6557ff7147f17ad_master.jpg',6),('f731ecad-71cd-43b6-810d-a724c3c58aa4-1713163768418','Đầm Midi Cúp Ngực Kim Sa Lấp Lánh',1280000,'brand2','Cam kết:                                                                        ','https://product.hstatic.net/200000456167/product/linhphung-79__1__b5fd45066829455b8e92e08d42af2f84_master.jpg',20);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `product_id` varchar(255) NOT NULL,
  `category_id` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES ('140d9d97-abfb-4b25-917f-3290e2f81162-1713163768409','cate1'),('a94953aa-61b9-4614-b063-6e1227ca3997-1713163768413','cate1'),('f731ecad-71cd-43b6-810d-a724c3c58aa4-1713163768418','cate1'),('95285f98-4813-4ae3-85ec-b89c6a7e811a-1713163768429','cate2'),('def4c8fd-dfd9-4417-855f-f24e2c58141e-1713163768434','cate2'),('16eac33f-c447-4ae7-bc5e-3e9db1de55bd-1713163768426','cate3'),('7abfdef1-4786-47e2-b56c-d3fc3c855a58-1713163768451','cate3'),('c6006a3b-5702-4767-a796-3e6e18899057-1713163768447','cate3'),('7abfdef1-4786-47e2-b56c-d3fc3c855a58-1713163768451','cate5'),('c6006a3b-5702-4767-a796-3e6e18899057-1713163768447','cate5'),('16eac33f-c447-4ae7-bc5e-3e9db1de55bd-1713163768426','cate6'),('645f1d79-179f-45e9-a7b0-771893b98fda-1713163768443','cate6'),('66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422','cate7'),('66f8f2be-6081-4b8f-be01-a09c5df807d2-1713163768422','cate8'),('645f1d79-179f-45e9-a7b0-771893b98fda-1713163768443','cate9'),('c022c43b-c9fc-4669-8982-79f35fc37914-1713163768439','cate9');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `shipment_id` varchar(255) NOT NULL,
  `shipment_cost` double DEFAULT NULL,
  `shipment_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES ('53493b7d-d7db-4bdd-89d7-ba291b53e5b7-1712892195350',25000,'Grap'),('b8bedbed-3af0-48da-8ee9-8cc2222c46b1-1712892202490',20000,'GHN');
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 21:04:20
