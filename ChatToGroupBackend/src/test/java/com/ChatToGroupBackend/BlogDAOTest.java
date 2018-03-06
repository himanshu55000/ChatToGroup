package com.ChatToGroupBackend;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ChatToGroupBackend.dao.BlogDAO;
import com.ChatToGroupBackend.model.Blog;
import com.ChatToGroupBackend.model.UserDetails;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlogDAOTest {
	static AnnotationConfigApplicationContext context;
	static Blog blog=new Blog();
	static BlogDAO blogDAO;
	@BeforeClass
	public static void setup(){
		context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		blogDAO=(BlogDAO)context.getBean("blogDAO");
		blog.setBlog_title("Title");
		blog.setApproved("NA");
		UserDetails user=new UserDetails();
		user.setUsername("username");
		blog.setPosted_by(user);
		blog.setPosted_on(new Date());
		blog.setBlog_description("description");
	}

	@Test
	public void test1_InsertOrUpdateBlog() {
		assertEquals(true, blogDAO.insertOrUpdateBlog(blog));
	}

	@Test
	public void test2_GetBlogs() {
		assertNotEquals(null, blogDAO.getBlogs("NA"));
	}

	@Test
	public void test3_GetBlogById() {
		System.out.println(blog.getBlog_id());
		assertNotEquals(null, blogDAO.getBlogById(blog.getBlog_id()));
	}

	@Test
	public void test4_GetBlogsByUser() {
		assertNotEquals(null, blogDAO.getBlogsByUser("username"));
	}

	@Test
	public void test5_DeleteBlog() {
		assertEquals(true, blogDAO.deleteBlog(blog));
	}
	
	@AfterClass
	public static void tearDown(){
		context.close();
	}

}
