package com.example.ManageStore.Model;

public class TKNghiPhep {
	private int  idnv;
	private String hoten,chucvu;
	private int ngayphepduoccap, ngayphepdadung, ngayphepconlai;
	public TKNghiPhep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TKNghiPhep(int idnv, String hoten, String chucvu, int ngayphepduoccap, int ngayphepdadung,
			int ngayphepconlai) {
		super();
		this.idnv = idnv;
		this.hoten = hoten;
		this.chucvu = chucvu;
		this.ngayphepduoccap = ngayphepduoccap;
		this.ngayphepdadung = ngayphepdadung;
		this.ngayphepconlai = ngayphepconlai;
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
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public int getNgayphepduoccap() {
		return ngayphepduoccap;
	}
	public void setNgayphepduoccap(int ngayphepduoccap) {
		this.ngayphepduoccap = ngayphepduoccap;
	}
	public int getNgayphepdadung() {
		return ngayphepdadung;
	}
	public void setNgayphepdadung(int ngayphepdadung) {
		this.ngayphepdadung = ngayphepdadung;
	}
	public int getNgayphepconlai() {
		return ngayphepconlai;
	}
	public void setNgayphepconlai(int ngayphepconlai) {
		this.ngayphepconlai = ngayphepconlai;
	}
	
}
