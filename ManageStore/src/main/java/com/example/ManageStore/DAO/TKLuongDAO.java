package com.example.ManageStore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ManageStore.Model.Luong;
import org.springframework.stereotype.Repository;

@Repository
public class TKLuongDAO extends DAO {
	private static final String SELECT_LUONG = "SELECT nhanvien.id,  nhanvien.hoten,  nhanvien.sdt,  nhanvien.diachi,  nhanvien.chucvu, luong.luongcoban\r\n"
			+ "FROM manager_store.luong JOIN manager_store.nhanvien  ON luong.idnv = nhanvien.id";
	private static final String SELECT_TK_LUONG = "SELECT  n.id,  n.hoten,  n.sdt,  n.diachi,  n.chucvu,l.luongcoban,l.thuong,\r\n"
			+ "        COUNT(DISTINCT DATE(c.thoigianvao)) AS so_ngay_di_lam,\r\n"
			+ "        SUM(TIMESTAMPDIFF(HOUR, c.thoigianvao, c.thoigianra) - 8) AS gio_ot\r\n"
			+ "FROM manager_store.luong l\r\n"
			+ "JOIN manager_store.nhanvien n ON n.id = l.idnv\r\n"
			+ "JOIN manager_store.chamcong c ON c.idnhanvien = n.id\r\n"
			+ "WHERE MONTH(c.thoigianvao) = ?\r\n"
			+ "GROUP BY n.id, n.hoten, n.sdt, n.diachi, n.chucvu;";
	public ResponseEntity<?> getLuong (){
		List<Luong> list = new ArrayList<>();
		try(Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LUONG);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idnv = rs.getInt("id");
				String ten = rs.getString("hoten");
				String sdt = rs.getString("sdt");
				String diachi = rs.getString("diachi");
				String chucvu = rs.getString("chucvu");
				float luongcoban = rs.getFloat("luongcoban");
				list.add(new Luong(idnv, ten, sdt, diachi, chucvu, luongcoban));
			}
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<?> getTKLuong (int month){
		List<Luong> luongs = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SELECT_TK_LUONG);
			ps.setInt(1, month);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int idnv = rs.getInt("id");
				String ten = rs.getString("hoten");
				String sdt = rs.getString("sdt");
				String diachi = rs.getString("diachi");
				String chucvu = rs.getString("chucvu");
				float luongcoban = rs.getFloat("luongcoban");
				float thuong = rs.getFloat("thuong");
				int sndi = rs.getInt("so_ngay_di_lam");
				int ot = rs.getInt("gio_ot");
				luongs.add(new Luong(idnv, ten, sdt, diachi, chucvu, luongcoban, thuong, sndi, ot));
			}
			return ResponseEntity.ok().body(luongs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
}
