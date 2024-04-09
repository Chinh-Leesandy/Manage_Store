package com.example.ManageStore.Model;

import java.sql.Timestamp;

public class TimeKeeping {
	private int id, idnhanvien;
	private Timestamp thoigianvao, thoigianra;
	private String ten, chucvu;
	public TimeKeeping() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public TimeKeeping(int id, int idnhanvien, Timestamp thoigianvao, Timestamp thoigianra) {
		super();
		this.id = id;
		this.idnhanvien = idnhanvien;
		this.thoigianvao = thoigianvao;
		this.thoigianra = thoigianra;
	}
	
	public TimeKeeping(int id, int idnhanvien, Timestamp thoigianvao, Timestamp thoigianra, String ten, String chucvu) {
		super();
		this.id = id;
		this.idnhanvien = idnhanvien;
		this.thoigianvao = thoigianvao;
		this.thoigianra = thoigianra;
		this.ten = ten;
		this.chucvu = chucvu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdnhanvien() {
		return idnhanvien;
	}
	public void setIdnhanvien(int idnhanvien) {
		this.idnhanvien = idnhanvien;
	}
	public Timestamp getThoigianvao() {
		return thoigianvao;
	}
	public void setThoigianvao(Timestamp thoigianvao) {
		this.thoigianvao = thoigianvao;
	}
	public Timestamp getThoigianra() {
		return thoigianra;
	}
	public void setThoigianra(Timestamp thoigianra) {
		this.thoigianra = thoigianra;
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
	
}
