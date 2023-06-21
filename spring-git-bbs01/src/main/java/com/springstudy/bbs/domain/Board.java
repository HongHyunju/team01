package com.springstudy.bbs.domain;

import java.sql.Timestamp;

public class Board {
	
	private int no;
	private String code;
	private String name;
	private String writer;
	private int price;
	private String ram;
	private String ssd;
	private String inch;
	private String desciption;
	private String pass;
	private String file1;
	
	public Board() {}
	
	public Board(int no, String code, String name, String writer, int price, String ram, String ssd, String inch, String desciption, String pass, String file1) {
		this.no = no;
		this.code = code;
		this.name = name;
		this.writer = writer;
		this.price = price;
		this.ram = ram;
		this.ssd = ssd;
		this.inch = inch;
		this.desciption = desciption;
		this.pass = pass;
		this.file1 = file1;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getInch() {
		return inch;
	}

	public void setInch(String inch) {
		this.inch = inch;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
	
}
