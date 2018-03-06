package com.ChatToGroupBackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	private String job_title;
	private String job_description;
	private String skills_required;
	private int salary,vacancies,experience;
	private String location;
	private String company_name;
	private long contact;
	private Date posted_on;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getJob_description() {
		return job_description;
	}	
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public String getSkills_required() {
		return skills_required;
	}
	public void setSkills_required(String skills_required) {
		this.skills_required = skills_required;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Date getPosted_on() {
		return posted_on;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public void setPosted_on(Date posted_on) {
		this.posted_on = posted_on;
	}
	public int getVacancies() {
		return vacancies;
	}
	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}	
}
	