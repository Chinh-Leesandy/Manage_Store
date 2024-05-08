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
	private static final String DELETE_EMPLOYEE_DEPENDENCIES = "DELETE FROM nghiphep WHERE idnhanvien = ?";
	private static final String DELETE_EMPLOYEE_DEPENDENCIES2 = "DELETE FROM chamcong WHERE idnhanvien = ?";
	
	public ResponseEntity<List<Employee>> selectAllEmployee() {
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
	
	public ResponseEntity<Employee> selectEmployee (int id) {
		Employee employee = new Employee("Nguyễn Quang", "0948256153", "quang@gmail.com", "Thanh Trì - Hà Nội", "Quản lý loại hàng", "Quang", "Quang@256", Date.valueOf("2024-05-01"));
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
	
	public ResponseEntity<Employee> insertEmployee(Employee employee)  {
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

	public ResponseEntity<Employee> updateEmployee(Employee employee){
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
	
	public ResponseEntity<String> deleteEmployee(int id) {
		try (Connection connection = getConnection()) {
	        connection.setAutoCommit(false);
	        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_DEPENDENCIES)) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            connection.rollback(); 
	            e.printStackTrace();
	            return ResponseEntity.internalServerError().body("Failed to delete employee dependencies");
	        }
	        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_DEPENDENCIES2)) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            connection.rollback(); 
	            e.printStackTrace();
	            return ResponseEntity.internalServerError().body("Failed to delete employee dependencies2");
	        }
	        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            connection.rollback(); 
	            e.printStackTrace();
	            return ResponseEntity.internalServerError().body("Failed to delete employee");
	        }
	        connection.commit(); 
	        return ResponseEntity.ok("Employee deleted successfully");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ResponseEntity.internalServerError().build();
	}
		
}
