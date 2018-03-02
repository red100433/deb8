package com.deb8.entity;

public class Heart {
	private int id;
	private int giverId;
	private int postId;
	private long time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGiverId() {
		return giverId;
	}

	public void setGiverId(int giverId) {
		this.giverId = giverId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
