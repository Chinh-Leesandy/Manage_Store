CREATE TABLE `manager_store`.`nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(45) NOT NULL,
  `sdt` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `diachi` varchar(45) NOT NULL,
  `chucvu` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `manager_store`.`nhanvien` VALUES (1,'Nguyễn Chinh','0948266581','chinh@gmail.com','Thanh Trì - Hà Nội','Quản lý loại hàng','Chinh','Chinh@123'),(2,'Nguyễn Cường','0315914471','cuonng@gmail.com','Hà Đông - Hà Nội','Quản lý nhân viên','Cuong','Cuong@234');

CREATE TABLE `manager_store`.`loaimathang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) NOT NULL,
  `nhacungcap` varchar(45) NOT NULL,
  `thoigiannhap` date NOT NULL,
  `soluong` int NOT NULL,
  `vitritrungbay` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `manager_store`.`loaimathang` VALUES (1,'Bìa hồ sơ','Stationery Inc','2024-04-02',3,NULL),(2,'Bút','Deli','2024-03-15',10,NULL);
CREATE TABLE `manager_store`.`chamcong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idnhanvien` int NOT NULL,
  `thoigianvao` timestamp NOT NULL,
  `thoigianra` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nhanvien_idx` (`idnhanvien`),
  CONSTRAINT `idx_nhanvien` FOREIGN KEY (`idnhanvien`) REFERENCES `nhanvien` (`id`)
);
INSERT INTO `manager_store`.`chamcong` VALUES (1,1,'2024-03-10 01:30:00','2024-03-10 10:45:00'),(2,2,'2024-03-20 02:15:00','2024-03-20 11:00:00'),(3,1,'2024-03-25 03:00:00','2024-03-25 09:30:00');

CREATE TABLE `manager_store`.`nghiphep` (
  `idnghiphep` INT NOT NULL AUTO_INCREMENT,
  `idnhanvien` INT NOT NULL,
  `thoigian` DATE NOT NULL,
  `songaynghi` INT NOT NULL,
  PRIMARY KEY (`idnghiphep`),
  INDEX `idx_idnhanvien1_idx` (`idnhanvien` ASC) ,
  CONSTRAINT `idx_idnhanvien1`
    FOREIGN KEY (`idnhanvien`)
    REFERENCES `manager_store`.`nhanvien` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
INSERT INTO `manager_store`.`nghiphep` (`idnhanvien`, `thoigian`, `songaynghi`) VALUES ('1', '2024-03-01', '2');
INSERT INTO `manager_store`.`nghiphep` (`idnhanvien`, `thoigian`, `songaynghi`) VALUES ('2', '2024-04-01', '3');






SELECT np.idnhanvien, n.hoten,n.chucvu,
    IF(TIMESTAMPDIFF(MONTH, n.ngaybatdaulv, CURDATE()) >= 12, 12,
        ROUND(12 * TIMESTAMPDIFF(MONTH, n.ngaybatdaulv, CURDATE()) / 12, 0)
    ) AS ngayphepduoccap,
    COALESCE(SUM(np.songaynghi), 0) AS ngayphepdasudung,
    IF(TIMESTAMPDIFF(MONTH, n.ngaybatdaulv, CURDATE()) >= 12, 12,
        ROUND(12 * TIMESTAMPDIFF(MONTH, n.ngaybatdaulv, CURDATE()) / 12, 0)
    ) - COALESCE(SUM(np.songaynghi), 0) AS ngayphepconlai
FROM manager_store.nhanvien n
LEFT JOIN manager_store.nghiphep np ON n.id = np.idnhanvien
WHERE YEAR(np.thoigian) = YEAR(CURDATE()) AND idnhanvien = 1
GROUP BY np.idnhanvien;












