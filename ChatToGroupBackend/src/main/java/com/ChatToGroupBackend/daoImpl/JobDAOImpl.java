package com.ChatToGroupBackend.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ChatToGroupBackend.dao.JobDAO;
import com.ChatToGroupBackend.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {
	@Autowired
	SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insertOrUpdateJob(Job job) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteJob(Job job) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Job> getJob() {
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Job> list = session.createQuery("from Job").list();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public Job getJobById(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Job job = session.get(Job.class, id);
			return job;
		} catch (Exception e) {
			return null;
		}
	}

}
