package com.example.ManageStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ManageStore.DAO.TKLuongDAO;

@RestController
@CrossOrigin
public class TKLuongController {
	@Autowired
	private TKLuongDAO  luongDAO;
	
	@GetMapping("/luong")
	public ResponseEntity<?> getLuong () throws Exception{
		return luongDAO.getLuong();
	}
	
	@GetMapping("/TKLuong/{month}")
	public ResponseEntity<?> getTKSalary(@PathVariable String month) throws Exception{
		int m = Integer.parseInt(month);
		return luongDAO.getTKLuong(m);
	}
}
