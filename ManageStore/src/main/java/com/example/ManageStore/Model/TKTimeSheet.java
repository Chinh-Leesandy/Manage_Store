package com.example.ManageStore.Model;


public class TKTimeSheet {
	private int idnhanvien;
	private String ten,sdt, diachi, chucvu;
	private int songaydilam;
	private int gioot;
	public TKTimeSheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TKTimeSheet(int idnhanvien, String ten, String sdt, String diachi, String chucvu, int songaydilam,
			int gioot) {
		super();
		this.idnhanvien = idnhanvien;
		this.ten = ten;
		this.sdt = sdt;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.songaydilam = songaydilam;
		this.gioot = gioot;
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

	public int getGioot() {
		return gioot;
	}

	public void setGioot(int gioot) {
		this.gioot = gioot;
	}

	public int getIdnhanvien() {
		return idnhanvien;
	}
	public void setIdnhanvien(int idnhanvien) {
		this.idnhanvien = idnhanvien;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public int getSongaydilam() {
		return songaydilam;
	}
	public void setSongaydilam(int songaydilam) {
		this.songaydilam = songaydilam;
	}
	
	
}
