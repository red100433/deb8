package com.deb8.entity;

import com.deb8.model.SimpleUser;

public class Post {
	private int id;
	private SimpleUser writer;
	private Topic topic;

	private long time;
	private String contents;
	private int hearts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleUser getWriter() {
		return writer;
	}

	public void setWriter(SimpleUser writer) {
		this.writer = writer;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHearts() {
		return hearts;
	}

	public void setHearts(int hearts) {
		this.hearts = hearts;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Post) {
			return this.id == ((Post)obj).getId();
		}

		return false;
	}

}