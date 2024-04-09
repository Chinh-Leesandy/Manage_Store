package com.example.ManageStore.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ManageStore.Model.Employee;



public class EmployeeDAO extends DAO {
	private static final String SELECT_ALL = "select * from nhanvien";
	private static final String SELECT_EMPLOYEE = "select * from nhanvien where id = ?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO nhanvien (hoten, sdt, email, diachi, chucvu, username, password,ngaybatdaulv) VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_EMPLOYEE = "UPDATE nhanvien SET hoten = ?, sdt = ?, email = ?, diachi = ?, chucvu = ?, username = ?, password = ?, ngaybatdaulv= ? WHERE id = ?";
	private static final String DELETE_EMPLOYEE ="DELETE FROM nhanvien WHERE id = ?";
	
	public ResponseEntity<?> selectAllEmployee() {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String hoten = rs.getString("hoten");
				String sdt = rs.getString("sdt");
				String email = rs.getString("email");
				String diachi = rs.getString("diachi");
				String chucvu = rs.getString("chucvu");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Date time = rs.getDate("ngaybatdaulv");
				employees.add(new Employee(id, hoten, sdt, email, diachi, chucvu, username, password, time));
			}
			return ResponseEntity.ok().body(employees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<?> selectEmployee (int id) {
		Employee employee = new Employee();
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String hoten = rs.getString("hoten");
				String sdt = rs.getString("sdt");
				String email = rs.getString("email");
				String diachi = rs.getString("diachi");
				String chucvu = rs.getString("chucvu");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Date time = rs.getDate("ngaybatdaulv");
				employee = new Employee(id, hoten, sdt, email, diachi, chucvu, username, password, time);
			}
			return ResponseEntity.ok().body(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<?> insertEmployee(Employee employee)  {
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, employee.getHoten());
			ps.setString(2, employee.getSdt());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getDiachi());
			ps.setString(5, employee.getChucvu());
			ps.setString(6, employee.getUsername());
			ps.setString(7, employee.getPassword());
			ps.setDate(8, employee.getNgaybatdaulam());
			ps.executeUpdate();
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			e.printStackTrace();
        }
		return ResponseEntity.internalServerError().build();
	}

	public ResponseEntity<?> updateEmployee(Employee employee){
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE);
			ps.setString(1, employee.getHoten());
			ps.setString(2, employee.getSdt());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getDiachi());
			ps.setString(5, employee.getChucvu());
			ps.setString(6, employee.getUsername());
			ps.setString(7, employee.getPassword());
			ps.setDate(8, employee.getNgaybatdaulam());
			ps.setInt(9, employee.getId());
			ps.executeUpdate();
			return ResponseEntity.ok(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<?> deleteEmployee(int id) {
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE);
			ps.setInt(1, id);
			ps.executeUpdate();
			return ResponseEntity.ok("Delete Employee");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
		
}
