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
import com.ChatToGroupBackend.model.BlogComment;
import com.ChatToGroupBackend.model.Error;
@Controller
public class BlogController {
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	UserDetailsDAO userDetailsDAO;
	private int blog_id;
	@RequestMapping(value="/addBlog",method=RequestMethod.POST)
	public ResponseEntity<?> addBlog(@RequestBody Blog blog,HttpSession session){
		UserDetails user=userDetailsDAO.getUserDetails((String)session.getAttribute("username"));
		if(user==null){
			Error error=new Error(5,"Unauthorized User!!");
    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
    	}
		try{
			if(blog.getBlog_id()==0)
				blog.setApproved("NA");
			if(session.getAttribute("role").equals("admin"))
				blog.setApproved("A");
			blog.setPosted_on(new Date());
			blog.setPosted_by(user);
			blogDAO.insertOrUpdateBlog(blog);
			blog_id=blog.getBlog_id();
			return new ResponseEntity<Void>(HttpStatus.OK);
	    }
		catch(Exception e){
			Error error=new Error(1,"Unable to add Blog!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}
	
	@RequestMapping(value="/getAllBlog/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlog(@PathVariable String approved,HttpSession session){
		/*if(session.getAttribute("username")==null){
    		Error error=new Error(5,"Unauthorized User!!");
    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
    	}*/
		List<Blog> list=blogDAO.getBlogs(approved);
		//System.out.println(list.size());
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/getAllBlogByUser/{username}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogByUser(@PathVariable String username){
		List<Blog> list=blogDAO.getBlogsByUser(username);
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);	
	}
//rakesh.pandit@niit.com
	@RequestMapping(value="/getBlogById/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogById(@PathVariable int id){
		Blog blog=blogDAO.getBlogById(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/approveBlog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> approveBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			else if(!session.getAttribute("role").equals("admin")){
				Error error=new Error(6,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);	
			}
			Blog blog=blogDAO.getBlogById(id);
			blog.setApproved("A");
			blogDAO.insertOrUpdateBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(1,"Unable to approve Blog!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}
	
	@RequestMapping(value="/rejectBlog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> rejectBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			else if(!session.getAttribute("role").equals("admin")){
				Error error=new Error(6,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);	
			}
			Blog blog=blogDAO.getBlogById(id);
			blog.setApproved("R");
			blogDAO.insertOrUpdateBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(1,"Unable to Reject Blog!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}

	@RequestMapping(value="/deleteBlog/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteBlog(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			Blog blog=blogDAO.getBlogById(id);
			if(blog.getPosted_by().getUsername().equals(session.getAttribute("username"))){
			blogDAO.deleteBlog(blog);
			return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else{
				Error error=new Error(6,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
    	}catch(Exception e){
    		System.out.print(e);
    		Error error=new Error(1,"Unable to delete Blog!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	}
	
	@RequestMapping(value="/addBlogComment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		String username=(String)session.getAttribute("username");	
		if(username==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			try{
				UserDetails user=userDetailsDAO.getUserDetails(username);
				blogComment.setCommentedBy(user);
				blogComment.setCommentedOn(new Date());
				blogDAO.addBlogComment(blogComment);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			catch(Exception e){	
				Error error=new Error(1,"Unable to add BlogComment!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
		}

	@RequestMapping(value="/getAllBlogComment/{blog_id}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogComment(@PathVariable int blog_id){
	List<BlogComment> list=blogDAO.getAllBlogComment(blog_id);
	return new ResponseEntity<List<BlogComment>>(list,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/addBlogPicture",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogImage(@RequestParam("image") MultipartFile image,HttpSession session)
	{	String imgpath=session.getServletContext().getRealPath("/resources/images/");
		String file_info=imgpath+blog_id+".jpg";
		System.out.println(file_info);
		File f=new File(file_info);
		if(!image.isEmpty()){
			try{
			byte buff[]=image.getBytes();
			BufferedOutputStream bs=new BufferedOutputStream(new FileOutputStream(f));
			bs.write(buff);
			bs.close();
			return new ResponseEntity<Void>(HttpStatus.OK);
	    	}
			catch(Exception e){
	    		System.out.print(e);
	    		Error error=new Error(1,"Unable to upload profile picture!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
			}
		else{
    		Error error=new Error(1,"Unable to upload profile picture!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	


