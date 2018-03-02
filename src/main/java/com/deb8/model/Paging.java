package com.deb8.model;

public class Paging {
	private final int DEFAULT_PAGE = 10;

	private String target;
	private int page;
	private long lastId;

	public Paging(String target, long lastResultTime) {
		this.target = target;
		this.lastId = lastResultTime;
		page = DEFAULT_PAGE;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastResultTime) {
		this.lastId = lastResultTime;
	}
}
