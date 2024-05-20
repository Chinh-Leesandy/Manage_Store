package com.example.ManageStore.Model;

import jakarta.validation.constraints.*;

import java.sql.Date;

public class ProductType {
	private int id;
	@NotBlank (message = "Tên loại mặt hàng không được để trống.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng.")
	private String ten;
	@NotBlank(message = "Tên nhà cung cấp không được để trống.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng.")
	private String ncc;
	@NotNull(message = "Thời gian nhập không được để trống.")
	private Date thoigiannhap;
	@NotNull(message = "Số lượng loại mặt hàng không được để trống.")
	@Min(value = 1, message = "Số lượng loại mặt hàng phải lớn hơn hoặc bằng 1.")
	@Max(value = 100, message = "Số lượng loại mặt hàng phải nhỏ hơn hoặc bằng 100.")
	private int soluong;
	private String vitri;
	public ProductType() {
		super();
	}
	
	public ProductType(String ten, String ncc, Date thoigiannhap, int soluong, String vitri) {
		super();
		this.ten = ten;
		this.ncc = ncc;
		this.thoigiannhap = thoigiannhap;
		this.soluong = soluong;
		this.vitri = vitri;
	}

	public ProductType(int id, String ten, String ncc, Date thoigiannhap, int soluong, String vitri) {
		super();
		this.id = id;
		this.ten = ten;
		this.ncc = ncc;
		this.thoigiannhap = thoigiannhap;
		this.soluong = soluong;
		this.vitri = vitri;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getNcc() {
		return ncc;
	}
	public void setNcc(String ncc) {
		this.ncc = ncc;
	}
	public Date getThoigiannhap() {
		return thoigiannhap;
	}
	public void setThoigiannhap(Date thoigiannhap) {
		this.thoigiannhap = thoigiannhap;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getVitri() {
		return vitri;
	}
	public void setVitri(String vitri) {
		this.vitri = vitri;
	}
	
}
