package com.ChatToGroupBackend;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ChatToGroupBackend.dao.JobDAO;
import com.ChatToGroupBackend.model.Job;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobDAOTest {
	static AnnotationConfigApplicationContext context;
	static Job job=new Job();
	static JobDAO jobDAO;
	@BeforeClass
	public static void setup(){
		context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		jobDAO=(JobDAO)context.getBean("jobDAO");
		job.setJob_title("Title");
		job.setCompany_name("company_name");
		job.setContact(9999999999l);
		job.setExperience(12);
		job.setLocation("location");
		job.setSalary(12000);
		job.setVacancies(1);
		job.setPosted_on(new Date());
		job.setJob_description("description");
	}

	@Test
	public void test1_InsertOrUpdateJob() {
		assertEquals(true, jobDAO.insertOrUpdateJob(job));
	}

	@Test
	public void test2_GetJob() {
		assertNotEquals(null, jobDAO.getJob());
	}

	@Test
	public void test3_GetJobById() {
		assertNotEquals(null, jobDAO.getJobById(job.getId()));
	}

	@Test
	public void test5_DeleteJob() {
		assertEquals(true, jobDAO.deleteJob(job));
	}
	
	@AfterClass
	public static void tearDown(){
		context.close();
	}

}
