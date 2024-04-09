package com.example.ManageStore.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ManageStore.DAO.EmployeeDAO;
import com.example.ManageStore.Model.Employee;
@RestController
@CrossOrigin
public class EmployeeController {
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	@GetMapping("/employee")
	public ResponseEntity<?> getEmployee() throws Exception{
		return employeeDAO.selectAllEmployee();
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeByID(@PathVariable String id){
		int idnv = Integer.parseInt(id);
		return employeeDAO.selectEmployee(idnv);
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> postEmployee (@RequestBody Employee employee){
		return employeeDAO.insertEmployee(employee);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<?> putEmployee (@RequestBody Employee employee){
		return employeeDAO.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmploye/{id}")
	public ResponseEntity<?> deleteEmployee (@PathVariable String id){
		int idnv = Integer.parseInt(id);
		return employeeDAO.deleteEmployee(idnv);
	}
}