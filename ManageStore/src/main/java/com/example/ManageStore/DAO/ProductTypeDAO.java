package com.example.ManageStore.DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ManageStore.Model.ProductType;

public class ProductTypeDAO extends DAO{
	private static final String SELECT_ALL = "SELECT * FROM loaimathang";
	private static final String SELECT_PRODUCT_TYPE = "SELECT * FROM loaimathang WHERE id = ?";
	private static final String INSERT_PRODUCT_TYPE = "INSERT INTO loaimathang (ten,nhacungcap,thoigiannhap,soluong,vitritrungbay) VALUES (?,?,?,?,?)";
	private static final String UPDATE_PRODUCT_TYPE = "UPDATE loaimathang SET ten = ?, nhacungcap = ?, thoigiannhap =?, soluong=?, vitritrungbay = ? WHERE id = ?";
	private static final String DELETE_PRODUCT_TYPE = "DELETE FROM loaimathang WHERE id = ?";
	
	public ResponseEntity<List<ProductType>> selectAll(){
		List<ProductType> list = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String ten = rs.getString("ten");
				String ncc = rs.getString("nhacungcap");
				Date date = rs.getDate("thoigiannhap");
				int sl = rs.getInt("soluong");
				String vitri =  rs.getString("vitritrungbay");
				list.add(new ProductType(id, ten, ncc, date, sl, vitri));
			}
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<ProductType> selectProductType(int id) {
		ProductType productType = new ProductType();
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_TYPE);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("ten");
				String ncc = rs.getString("nhacungcap");
				Date date = rs.getDate("thoigiannhap");
				int sl = rs.getInt("soluong");
				String vitri =  rs.getString("vitritrungbay");
				productType = new ProductType(id, ten, ncc, date, sl, vitri);
			}
			return ResponseEntity.ok().body(productType);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<ProductType> insertProductType(ProductType productType) {
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_TYPE);
			ps.setString(1, productType.getTen());
			ps.setString(2, productType.getNcc());
			ps.setDate(3, productType.getThoigiannhap());
			ps.setInt(4, productType.getSoluong());
			ps.setString(5, productType.getVitri());
			ps.executeUpdate();
			return ResponseEntity.ok(productType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<ProductType> updateProductTyppe(ProductType productType){
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_TYPE);
			ps.setString(1, productType.getTen());
			ps.setString(2, productType.getNcc());
			ps.setDate(3, productType.getThoigiannhap());
			ps.setInt(4, productType.getSoluong());
			ps.setString(5, productType.getVitri());
			ps.setInt(6, productType.getId());
			ps.executeUpdate();
			return ResponseEntity.ok(productType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<String> deleteProductType (int id){
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_TYPE);
			ps.setInt(1, id);
			ps.executeUpdate();
			return ResponseEntity.ok("Delete Product Type");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().build();
	}

	
}
