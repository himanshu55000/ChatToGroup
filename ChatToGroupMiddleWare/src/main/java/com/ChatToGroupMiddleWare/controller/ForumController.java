package com.ChatToGroupMiddleWare.controller;

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

import com.ChatToGroupBackend.dao.ForumDAO;
import com.ChatToGroupBackend.dao.UserDetailsDAO;
import com.ChatToGroupBackend.model.UserDetails;
import com.ChatToGroupBackend.model.Forum;

@Controller
public class ForumController {
	@Autowired
	ForumDAO forumDAO;
	@Autowired
	UserDetailsDAO userDetailsDAO;

	@RequestMapping(value = "/addForum", method = RequestMethod.POST)
	public ResponseEntity<?> addForum(@RequestBody Forum forum, HttpSession session) {
		if(session.getAttribute("username")==null){
    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    	}
		else if(!session.getAttribute("role").equals("admin")){
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);	
		}
		UserDetails user = userDetailsDAO.getUserDetails((String) session.getAttribute("username"));
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		try {
			forum.setPosted_on(new Date());
			forum.setPosted_by(user);
			forumDAO.insertOrUpdateForum(forum);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getAllForum", method = RequestMethod.GET)
	public ResponseEntity<?> getAllForum(HttpSession session) {
		List<Forum> list = forumDAO.getForums();
		return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/getForumById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getForumById(@PathVariable int id) {
		Forum forum = forumDAO.getForumById(id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteForum/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteForum(@PathVariable int id, HttpSession session) {
		if(session.getAttribute("username")==null){
    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    	}
		else if(!session.getAttribute("role").equals("admin")){
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);	
		}
		try {
			if (session.getAttribute("username") == null) {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
			Forum forum = forumDAO.getForumById(id);
			if (forum.getPosted_by().getUsername().equals(session.getAttribute("username"))) {
				forumDAO.deleteForum(forum);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.print(e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
