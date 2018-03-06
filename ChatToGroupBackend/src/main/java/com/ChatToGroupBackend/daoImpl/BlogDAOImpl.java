package com.ChatToGroupBackend.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ChatToGroupBackend.dao.BlogDAO;
import com.ChatToGroupBackend.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insertOrUpdateBlog(Blog blog) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Blog> getBlogs(String approved) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Blog> query = session.createQuery("from Blog where approved=:approved");
			query.setParameter("approved", approved);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	public Blog getBlogById(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Blog blog = session.get(Blog.class, id);
			return blog;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Blog> getBlogsByUser(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Blog> query = session.createQuery("from Blog where posted_by.username=:user");
			query.setParameter("user", username);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteBlog(Blog blog) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
