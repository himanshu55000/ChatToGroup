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

import com.ChatToGroupBackend.dao.ForumDAO;
import com.ChatToGroupBackend.dao.UserDetailsDAO;
import com.ChatToGroupBackend.model.UserDetails;
import com.ChatToGroupBackend.model.Forum;
import com.ChatToGroupBackend.model.ForumComment;
import com.ChatToGroupBackend.model.Error;
@Controller
public class ForumController {
	@Autowired
	ForumDAO forumDAO;
	@Autowired
	UserDetailsDAO userDetailsDAO;
	private int forum_id;
	@RequestMapping(value="/addForum",method=RequestMethod.POST)
	public ResponseEntity<?> addForum(@RequestBody Forum forum,HttpSession session){
		UserDetails user=userDetailsDAO.getUserDetails((String)session.getAttribute("username"));
		if(user==null){
			Error error=new Error(5,"Unauthorized User!!");
    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
    	}
		try{
			forum.setPosted_on(new Date());
			forum.setPosted_by(user);
			forumDAO.insertOrUpdateForum(forum);
			forum_id=forum.getForum_id();
			return new ResponseEntity<Void>(HttpStatus.OK);
	    }
		catch(Exception e){
			Error error=new Error(1,"Unable to add Forum!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}			
	}
	
	@RequestMapping(value="/getAllForum",method=RequestMethod.GET)
	public ResponseEntity<?> getAllForum(HttpSession session){
		List<Forum> list=forumDAO.getForums();
		return new ResponseEntity<List<Forum>>(list,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/getForumById/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getForumById(@PathVariable int id){
		Forum forum=forumDAO.getForumById(id);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);	
	}	

	@RequestMapping(value="/deleteForum/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteForum(@PathVariable int id,HttpSession session){
		try{
			if(session.getAttribute("username")==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			Forum forum=forumDAO.getForumById(id);
			if(forum.getPosted_by().getUsername().equals(session.getAttribute("username"))){
			forumDAO.deleteForum(forum);
			return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else{
				Error error=new Error(6,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
    	}catch(Exception e){
    		System.out.print(e);
    		Error error=new Error(1,"Unable to delete Forum!!");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	}
	
	@RequestMapping(value="/addForumComment",method=RequestMethod.POST)
	public ResponseEntity<?> addForumComment(@RequestBody ForumComment forumComment,HttpSession session){
		String username=(String)session.getAttribute("username");	
		if(username==null){
	    		Error error=new Error(5,"Unauthorized User!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    	}
			try{
				UserDetails user=userDetailsDAO.getUserDetails(username);
				forumComment.setCommentedBy(user);
				forumComment.setCommentedOn(new Date());
				forumDAO.addForumComment(forumComment);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			catch(Exception e){	
				Error error=new Error(1,"Unable to add ForumComment!!");
	    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
		}

	@RequestMapping(value="/getAllForumComment/{forum_id}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllForumComment(@PathVariable int forum_id){
	List<ForumComment> list=forumDAO.getAllForumComment(forum_id);
	return new ResponseEntity<List<ForumComment>>(list,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/addForumPicture",method=RequestMethod.POST)
	public ResponseEntity<?> addForumImage(@RequestParam("image") MultipartFile image,HttpSession session)
	{	String imgpath=session.getServletContext().getRealPath("/resources/images/");
		String file_info=imgpath+forum_id+".jpg";
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
	


