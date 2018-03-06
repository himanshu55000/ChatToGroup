package com.ChatToGroupBackend;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ChatToGroupBackend.dao.UserDetailsDAO;
import com.ChatToGroupBackend.model.UserDetails;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDetailsDAOTest {
	static AnnotationConfigApplicationContext context;
	static UserDetailsDAO userDetailsDAO;

	@BeforeClass
	public static void setup() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		userDetailsDAO = (UserDetailsDAO) context.getBean("userDetailsDAO");
	}

	@Test
	public void test1_InsertOrUpdateUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setFirstname("firstname");
		userDetails.setLastname("lastname");
		userDetails.setEmail("email");
		userDetails.setUsername("username");
		userDetails.setAddress("address");
		userDetails.setMobile(9999999);
		userDetails.setOnline_status(false);
		userDetails.setPassword("password");
		userDetails.setRole("role");
		assertEquals(true, userDetailsDAO.insertOrUpdateUserDetails(userDetails));
	}

	@Test
	public void test2_GetUserDetails() {
		assertNotEquals(null, userDetailsDAO.getUserDetails("username"));
	}

	@Test
	public void test3_GetUserDetailsByEmail() {
		assertNotEquals(null, userDetailsDAO.getUserDetailsByEmail("email"));
	}

	@AfterClass
	public static void tearDown(){
		context.close();
	}


}
