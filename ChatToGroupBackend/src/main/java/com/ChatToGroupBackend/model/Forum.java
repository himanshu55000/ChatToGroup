package com.ChatToGroupBackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Forum {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int forum_id;
	private String forum_title;
	@Lob
	private String forum_description;
	private Date posted_on;
	@ManyToOne
	private UserDetails posted_by;

	public UserDetails getPosted_by() {
		return posted_by;
	}

	public void setPosted_by(UserDetails posted_by) {
		this.posted_by = posted_by;
	}

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}

	public String getForum_title() {
		return forum_title;
	}

	public void setForum_title(String forum_title) {
		this.forum_title = forum_title;
	}

	public String getForum_description() {
		return forum_description;
	}

	public void setForum_description(String forum_description) {
		this.forum_description = forum_description;
	}

	public Date getPosted_on() {
		return posted_on;
	}

	public void setPosted_on(Date posted_on) {
		this.posted_on = posted_on;
	}
}
