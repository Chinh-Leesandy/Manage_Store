package com.example.ManageStore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ManageStore.Model.TKTimeSheet;
import com.example.ManageStore.Model.TimeKeeping;
public class TimeKeepingDAO extends DAO {
	private static final String SELECT_EMPLOYEE_TIME ="SELECT chamcong.id,idnhanvien,thoigianvao, thoigianra,nhanvien.hoten,nhanvien.chucvu\r\n"
			+ "FROM manager_store.chamcong JOIN manager_store.nhanvien  ON chamcong.idnhanvien = nhanvien.id";
	private static final String TK_MONTH = "SELECT idnhanvien, nhanvien.hoten,nhanvien.sdt, nhanvien.diachi, nhanvien.chucvu,\r\n"
			+ "	COUNT(DISTINCT DATE(thoigianvao)) AS so_ngay_di_lam,\r\n"
			+ "  SUM(TIMESTAMPDIFF(HOUR, thoigianvao, thoigianra) - 8) AS gio_ot\r\n"
			+ "	FROM manager_store.chamcong \r\n"
			+ "JOIN manager_store.nhanvien ON manager_store.chamcong.idnhanvien = nhanvien.id\r\n"
			+ " WHERE MONTH(thoigianvao) = ?\r\n"
			+ "GROUP BY idnhanvien;";
	public ResponseEntity<List<TimeKeeping>> selectEmployeeTime(){
		List<TimeKeeping> keepings = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_TIME);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idnv = rs.getInt("idnhanvien");
				Timestamp checkin = rs.getTimestamp("thoigianvao");
				Timestamp checkout = rs.getTimestamp("thoigianra");
				String ten = rs.getString("hoten");
				String chucvu = rs.getString("chucvu");
				keepings.add(new TimeKeeping(id, idnv, checkin, checkout, ten, chucvu));
			}
			return ResponseEntity.ok().body(keepings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<List<TKTimeSheet>> selectTK(int month){
		List<TKTimeSheet> sheets = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(TK_MONTH);
			ps.setInt(1, month);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int idnv = rs.getInt("idnhanvien");
				String ten = rs.getString("hoten");
				String sdt = rs.getString("sdt");
				String diachi = rs.getString("diachi");
				String chucvu = rs.getString("chucvu");
				int sndi = rs.getInt("so_ngay_di_lam");
				int ot = rs.getInt("gio_ot");
				sheets.add(new TKTimeSheet(idnv, ten, sdt, diachi, chucvu, sndi, ot));
			}
			return ResponseEntity.ok().body(sheets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
}
