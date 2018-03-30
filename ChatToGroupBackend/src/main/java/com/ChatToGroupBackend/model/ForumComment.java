package com.ChatToGroupBackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ForumComment {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@ManyToOne
@JoinColumn(name="forum_id")
private Forum forum;
@ManyToOne
@JoinColumn(name="username")
private UserDetails commentedBy;
private Date commentedOn;
private String commentText;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Forum getForum() {
	return forum;
}
public void setForum(Forum forum) {
	this.forum = forum;
}
public UserDetails getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(UserDetails commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public String getCommentText() {
	return commentText;
}
public void setCommentText(String commentText) {
	this.commentText = commentText;
}

}
