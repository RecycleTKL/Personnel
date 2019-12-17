package com.groupsix;

public class Item {// 数据公共�?

	private String no;// 编号
	private String name;// 名称

	public Item() {// 默认构�??
	}

	public Item(String no, String name) {// 传参
		this.no = no;
		this.name = name;
	}

	// 定义Getters和Setters方法
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 重写tostring()方法
	public String toString() {
		return getName();
	}
}
