package com.ChatToGroupBackend;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ChatToGroupBackend.dao.ForumDAO;
import com.ChatToGroupBackend.model.Forum;
import com.ChatToGroupBackend.model.UserDetails;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForumDAOTest {
	static AnnotationConfigApplicationContext context;
	static Forum forum = new Forum();
	static ForumDAO forumDAO;

	@BeforeClass
	public static void setup() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum.setForum_title("Title");
		UserDetails user = new UserDetails();
		user.setUsername("username");
		forum.setPosted_by(user);
		forum.setPosted_on(new Date());
		forum.setForum_description("description");
	}

	@Test
	public void test1_InsertOrUpdateForum() {
		assertEquals(true, forumDAO.insertOrUpdateForum(forum));
	}

	@Test
	public void test2_GetForums() {
		assertNotEquals(null, forumDAO.getForums());
	}

	@Test
	public void test3_GetForumById() {
		System.out.println(forum.getForum_id());
		assertNotEquals(null, forumDAO.getForumById(forum.getForum_id()));
	}

/*	@Test
	public void test4_GetForumsByUser() {
		assertNotEquals(null, forumDAO.getForumsByUser("username"));
	}
*/
	@Test
	public void test5_DeleteForum() {
		assertEquals(true, forumDAO.deleteForum(forum));
	}

	@AfterClass
	public static void tearDown() {
		context.close();
	}

}
