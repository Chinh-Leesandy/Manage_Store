package com.example.ManageStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ManageStore.DAO.TKNghiPhepDAO;

@RestController
@CrossOrigin
public class TKNghiPhepController {
	@Autowired
	 private TKNghiPhepDAO nghiPhepDAO;
	 @GetMapping("/nghiphep")
	 public ResponseEntity<?> getNghiPhep(){
		 return nghiPhepDAO.selectNP();
	 }
	 @GetMapping("/tknghiphep/{month}")
	 public ResponseEntity<?> getTKNghiPhep(@PathVariable String month){
		 int m = Integer.parseInt(month);
		 return nghiPhepDAO.selectTKNP(m);
	 }
}
