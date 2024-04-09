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

import com.example.ManageStore.DAO.ProductTypeDAO;
import com.example.ManageStore.Model.ProductType;

@RestController
@CrossOrigin
public class ProductTypeController {
	private ProductTypeDAO dao = new ProductTypeDAO();
	
	@GetMapping("/productType")
	public ResponseEntity<?> getProductType () throws Exception {
		return dao.selectAll();
	}
	
	@GetMapping("/productType/{id}")
	public ResponseEntity<?> getProductTypeId(@PathVariable String id){
		int idp = Integer.parseInt(id);
		return dao.selectProductType(idp);
	}
	
	@PostMapping("/addProductType")
	public ResponseEntity<?> postProductType(@RequestBody ProductType productType){
		return dao.insertProductType(productType);
	}
	
	@PutMapping("/updateProductType")
	public ResponseEntity<?> putProductType (@RequestBody ProductType productType){
		return dao.updateProductTyppe(productType);
	}
	
	@DeleteMapping("/deleteProductType/{id}")
	public ResponseEntity<?> deleteProductType (@PathVariable String id){
		int idp = Integer.parseInt(id);
		return dao.deleteProductType(idp);
	}
}
