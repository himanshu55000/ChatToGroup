package com.ChatToGroupBackend.dao;

import java.util.List;

import com.ChatToGroupBackend.model.Blog;

public interface BlogDAO {
	boolean insertOrUpdateBlog(Blog blog);
	List<Blog> getBlogs(String approved);
	Blog getBlogById(int id);
	List<Blog> getBlogsByUser(String username);
	boolean deleteBlog(Blog blog);
}
