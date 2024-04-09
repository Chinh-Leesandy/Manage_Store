package com.example.ManageStore.Model;

import java.sql.Date;

public class ProductType {
	private int id;
	private String ten, ncc;
	private Date thoigiannhap;
	private int soluong;
	private String vitri;
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
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
