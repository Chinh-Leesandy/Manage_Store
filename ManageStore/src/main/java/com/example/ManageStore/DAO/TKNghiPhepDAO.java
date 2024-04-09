package com.example.ManageStore.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ManageStore.Model.NghiPhep;
import com.example.ManageStore.Model.NghiPhepMonth;
import com.example.ManageStore.Model.TKNghiPhep;

public class TKNghiPhepDAO extends DAO {
	private static final String SELECT_NP = "SELECT id, idnhanvien,nhanvien.hoten,nhanvien.chucvu, thoigian,songaynghi\r\n"
			+ "FROM manager_store.nghiphep JOIN manager_store.nhanvien  ON nghiphep.idnhanvien = nhanvien.id";
	private static final String TK = "SELECT np.idnhanvien, n.hoten,n.chucvu,SUM(np.songaynghi) AS songaydanghi\r\n"
			+ "FROM manager_store.nhanvien n\r\n"
			+ "LEFT JOIN manager_store.nghiphep np ON n.id = np.idnhanvien\r\n"
			+ "WHERE MONTH(np.thoigian) = ?\r\n"
			+ "GROUP BY np.idnhanvien;";
	
	public ResponseEntity<?> selectNP(){
		List<NghiPhep> list = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SELECT_NP);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idnv = rs.getInt("idnhanvien");
				String ten = rs.getString("hoten");
				String chucvu = rs.getString("chucvu");
				Date time = rs.getDate("thoigian");
				int snn = rs.getInt("songaynghi");
				list.add(new NghiPhep(id, idnv, ten, chucvu, time, snn));
			}
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<?> selectTKNP (int month){
		List<NghiPhepMonth>nghiPhep = new ArrayList<>(); 
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(TK);
			ps.setInt(1, month);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idnhanvien");
				String ten = rs.getString("hoten");
				String chucvu = rs.getString("chucvu");
				int ndcp = rs.getInt("songaydanghi");
				nghiPhep.add(new NghiPhepMonth(id, ten, chucvu, ndcp));
			}
			return ResponseEntity.ok().body(nghiPhep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
}
