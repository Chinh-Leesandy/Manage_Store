package com.example.ManageStore.Model;

import java.sql.Date;

public class NghiPhep {
	private int id, idnv;
	private String ten, chucvu;
	private Date thoigiannghi;
	private int songaynghi;
	public NghiPhep() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NghiPhep(int id, int idnv, String ten, String chucvu, Date thoigiannghi, int songaynghi) {
		super();
		this.id = id;
		this.idnv = idnv;
		this.ten = ten;
		this.chucvu = chucvu;
		this.thoigiannghi = thoigiannghi;
		this.songaynghi = songaynghi;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdnv() {
		return idnv;
	}
	public void setIdnv(int idnv) {
		this.idnv = idnv;
	}
	public Date getThoigiannghi() {
		return thoigiannghi;
	}
	public void setThoigiannghi(Date thoigiannghi) {
		this.thoigiannghi = thoigiannghi;
	}
	public int getSongaynghi() {
		return songaynghi;
	}
	public void setSongaynghi(int songaynghi) {
		this.songaynghi = songaynghi;
	}
	
	
}
