package com.SparkLearning.Model;

public class WordsDAO {
	private String content;
	private int count;
	
	public WordsDAO(){}
	
	public WordsDAO(String content, int count){
		this.setContent(content);
		this.setCount(count);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
