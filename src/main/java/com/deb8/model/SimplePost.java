package com.deb8.model;

/**
 * 글 등록을 위해 사용하는 Post 객체의 간소한 버전
 * create 할 때는 SimplePost 객체를 이용해 생성하고
 * read 할 때는 Post 객체를 통해 모든 정보를 읽어온다.
 * 
 * @author sunyoung.choi
 * 
 */
public class SimplePost {
	// 이 id 필드는 generatedKey를 이용하기 위함이다  
	// Assign 용도로 사용하지 않는다
	private int id;
	private String userCode;
	private String topicCode;
	private String contents;
	private long time;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
