package com.stuinfo.model;

public class Page {
	private int start;
	private int rows;
	private int page;
	
	public Page() {
		super();
	}
	public Page(int page,int rows){
		super();
		this.rows=rows;
		this.page=page;
	}
	public int getStart() {
		return (page-1)*rows;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
