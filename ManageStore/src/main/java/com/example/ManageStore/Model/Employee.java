package com.example.ManageStore.Model;

import java.sql.Date;

public class Employee {
	private int id;
	private String hoten, sdt, email, diachi, chucvu, username, password;
	private Date ngaybatdaulam;

	public Employee() {
	}

	public Employee(int id, String hoten, String sdt, String email, String diachi, String chucvu, String username, String password, Date ngaybatdaulam) {
		this.id = id;
		this.hoten = hoten;
		this.sdt = sdt;
		this.email = email;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.username = username;
		this.password = password;
		this.ngaybatdaulam = ngaybatdaulam;
	}

	public Employee(String hoten, String sdt, String email, String diachi, String chucvu, String username, String password, Date ngaybatdaulam) {
		this.hoten = hoten;
		this.sdt = sdt;
		this.email = email;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.username = username;
		this.password = password;
		this.ngaybatdaulam = ngaybatdaulam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getNgaybatdaulam() {
		return ngaybatdaulam;
	}

	public void setNgaybatdaulam(Date ngaybatdaulam) {
		this.ngaybatdaulam = ngaybatdaulam;
	}
}

