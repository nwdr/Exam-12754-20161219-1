package com.practice.db.entities;

import java.util.Date;

public class Language {
	private Integer languageId;
	private String name;
	private Date last_update;
	
	public Language() {}
	public Language(Integer languageId, String name, Date last_update) {
		this.languageId = languageId;
		this.name = name;
		this.last_update = last_update;
	}
	
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
}
