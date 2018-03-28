package com.ChatToGroupBackend.dao;

import java.util.List;

import com.ChatToGroupBackend.model.Blog;
import com.ChatToGroupBackend.model.BlogComment;

public interface BlogDAO {
	boolean insertOrUpdateBlog(Blog blog);
	List<Blog> getBlogs(String approved);
	Blog getBlogById(int id);
	List<Blog> getBlogsByUser(String username);
	boolean deleteBlog(Blog blog);
	boolean addBlogComment(BlogComment blogComment);
	List<BlogComment> getAllBlogComment(int blog_id);
}
