package com.ChatToGroupMiddleWare.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ChatToGroupBackend.dao.BlogDAO;
import com.ChatToGroupBackend.dao.UserDetailsDAO;
import com.ChatToGroupBackend.model.UserDetails;
import com.ChatToGroupBackend.model.Blog;

@Controller
public class BlogController {
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	UserDetailsDAO userDetailsDAO;
	@RequestMapping(value="/addBlog",method=RequestMethod.POST)
	public ResponseEntity<?> addBlog(@RequestBody Blog blog,HttpSession session){
		UserDetails user=userDetailsDAO.getUserDetails((String)session.getAttribute("username"));
		if(user==null){
    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    	}
		try{
			if(blog.getBlog_id()==0)
				blog.setApproved("NA");
			blog.setPosted_on(new Date());
			blog.setPosted_by(user);
			blogDAO.insertOrUpdateBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
	    }
		catch(Exception e){
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}
	
	@RequestMapping(value="/getAllBlog/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlog(@PathVariable String approved,HttpSession session){
		List<Blog> list=blogDAO.getBlogs(approved);
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/getAllBlogByUser/{username}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogByUser(@PathVariable String username){
		List<Blog> list=blogDAO.getBlogsByUser(username);
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);	
	}

	@RequestMapping(value="/getBlogById/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogById(@PathVariable int id){
		Blog blog=blogDAO.getBlogById(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/approveBlog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> approveBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	    	}
			else if(!session.getAttribute("role").equals("admin")){
	    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);	
			}
			Blog blog=blogDAO.getBlogById(id);
			blog.setApproved("A");
			blogDAO.insertOrUpdateBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}
	
	@RequestMapping(value="/rejectBlog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> rejectBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	    	}
			else if(!session.getAttribute("role").equals("admin")){
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);	
			}
			Blog blog=blogDAO.getBlogById(id);
			blog.setApproved("R");
			blogDAO.insertOrUpdateBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}

	@RequestMapping(value="/deleteBlog/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	    	}
			Blog blog=blogDAO.getBlogById(id);
			if(blog.getPosted_by().getUsername().equals(session.getAttribute("username"))){
			blogDAO.deleteBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else{
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
    	}catch(Exception e){
    		System.out.print(e);
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	}
	
}
	


