package com.example.ManageStore.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import java.util.ArrayList;
import java.util.List;

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
	public ResponseEntity<?> postEmployee (@Valid @RequestBody Employee employee, BindingResult result){
		if(result.hasErrors()){
			result.getAllErrors().forEach(error -> {
				System.out.println(error.toString());
			});
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		return employeeDAO.insertEmployee(employee);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<?> putEmployee (@Valid @RequestBody Employee employee, BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		return employeeDAO.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmploye/{id}")
	public ResponseEntity<?> deleteEmployee (@PathVariable String id){
		int idnv = Integer.parseInt(id);
		return employeeDAO.deleteEmployee(idnv);
	}
}