package com.example.ManageStore.Model;

import jakarta.validation.constraints.*;

import java.sql.Date;

public class Employee {
	private int id;
	@NotBlank(message = "Tên nhân viên không được để trống.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Tên nhân viên chỉ được chứa chữ cái và khoảng trắng.")
	private String hoten;
	@NotBlank(message = "Số điện thoại không được để trống.")
	@Pattern(regexp = "^\\+?(\\d{3})\\)?[-. ]?(\\d{3})[-. ]?(\\d{4})$", message = "Số điện thoại không hợp lệ.")
	private String sdt;
	@NotBlank(message = "Email nhân viên không được để trống.")
	@Pattern(regexp = ".+@.+", message = "Email không hợp lệ.")
	private String email;
	@NotBlank(message = "Địa chỉ nhân viên không được để trống.")
	private String diachi;
	@NotBlank(message = "Chức vụ nhân viên không được để trống.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Chức vụ chỉ được chứa chữ cái và khoảng trắng.")
	private String chucvu;
	@NotBlank(message = "Tài khoản đăng nhập nhân viên không được để trống.")
	private String username;
	@NotBlank(message = "Mật khẩu nhân viên không được để trống.")
	@Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự.")
	@Pattern(regexp = ".*[a-z].*", message = "Mật khẩu phải chứa ít nhất một chữ viết thường.")
	@Pattern(regexp = ".*[A-Z].*", message = "Mật khẩu phải chứa ít nhất một chữ viết hoa.")
	@Pattern(regexp = ".*\\d.*", message = "Mật khẩu phải chứa ít nhất một số.")
	@Pattern(regexp = ".*\\W.*", message = "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.")
	private String password;
	@NotNull(message = "Ngày bắt đầu làm việc không được để trống.")
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

