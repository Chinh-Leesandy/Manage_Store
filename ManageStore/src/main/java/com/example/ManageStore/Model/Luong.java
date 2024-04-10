package com.example.ManageStore.Model;

public class Luong {
	private int idnv;
	private String hoten, sdt, diachi, chucvu;
	private float luongcoban, thuong;
	private int songaydilam, gioot;
	public Luong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Luong(int idnv, String hoten, String sdt, String diachi, String chucvu, float luongcoban, float thuong,
			int songaydilam, int gioot) {
		super();
		this.idnv = idnv;
		this.hoten = hoten;
		this.sdt = sdt;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.luongcoban = luongcoban;
		this.thuong = thuong;
		this.songaydilam = songaydilam;
		this.gioot = gioot;
	}
	public Luong(int idnv, String hoten, String sdt, String diachi, String chucvu, float luongcoban) {
		super();
		this.idnv = idnv;
		this.hoten = hoten;
		this.sdt = sdt;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.luongcoban = luongcoban;
	}
	public int getIdnv() {
		return idnv;
	}
	public void setIdnv(int idnv) {
		this.idnv = idnv;
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
	public float getLuongcoban() {
		return luongcoban;
	}
	public void setLuongcoban(float luongcoban) {
		this.luongcoban = luongcoban;
	}
	public float getThuong() {
		return thuong;
	}
	public void setThuong(float thuong) {
		this.thuong = thuong;
	}
	public int getSongaydilam() {
		return songaydilam;
	}
	public void setSongaydilam(int songaydilam) {
		this.songaydilam = songaydilam;
	}
	public int getGioot() {
		return gioot;
	}
	public void setGioot(int gioot) {
		this.gioot = gioot;
	}
	
}
