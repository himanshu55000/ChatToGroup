package com.ChatToGroupBackend.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ChatToGroupBackend.dao.ForumDAO;
import com.ChatToGroupBackend.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insertOrUpdateForum(Forum forum) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Forum> getForums() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Forum> query = session.createQuery("from Forum");
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	public Forum getForumById(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Forum forum = session.get(Forum.class, id);
			return forum;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Forum> getForumsByUser(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Forum> query = session.createQuery("from Forum where posted_by.username=:user");
			query.setParameter("user", username);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteForum(Forum forum) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
