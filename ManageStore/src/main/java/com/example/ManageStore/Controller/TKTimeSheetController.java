package com.example.ManageStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ManageStore.DAO.TimeKeepingDAO;

@RestController
@CrossOrigin
public class TKTimeSheetController {
	@Autowired
	private TimeKeepingDAO keepingDAO;
	 
	@GetMapping("/employeeTimeKeeping")
	public ResponseEntity<?> getEmployeeTimeKeeping () throws Exception{
		return keepingDAO.selectEmployeeTime();
	}
	
	@GetMapping("/TKTimeSheet/{month}")
	public ResponseEntity<?> getTKSalary(@PathVariable String month) throws Exception{
		int m = Integer.parseInt(month);
		return keepingDAO.selectTK(m);
	}
}
