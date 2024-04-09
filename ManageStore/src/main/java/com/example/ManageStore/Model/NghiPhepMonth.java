package com.example.ManageStore.Model;


public class NghiPhepMonth {
	private int idnv;
	private String ten, chucvu;
	private int songaynghi;
	public NghiPhepMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NghiPhepMonth(int idnv, String ten, String chucvu, int songaynghi) {
		super();
		this.idnv = idnv;
		this.ten = ten;
		this.chucvu = chucvu;
		this.songaynghi = songaynghi;
	}
	public int getIdnv() {
		return idnv;
	}
	public void setIdnv(int idnv) {
		this.idnv = idnv;
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
	public int getSongaynghi() {
		return songaynghi;
	}
	public void setSongaynghi(int songaynghi) {
		this.songaynghi = songaynghi;
	}
	
}
