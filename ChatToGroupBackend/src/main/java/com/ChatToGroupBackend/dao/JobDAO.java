package com.ChatToGroupBackend.dao;

import java.util.List;

import com.ChatToGroupBackend.model.ApplyForJob;
import com.ChatToGroupBackend.model.Job;

public interface JobDAO {
	public boolean insertOrUpdateJob(Job job);
	public boolean deleteJob(Job job);
	public List<Job> getJob();
	public Job getJobById(int id);
	public List<ApplyForJob> getAllAppliedUser(int jobId);
	public void applyForJob(ApplyForJob applyForJob);
	public boolean checkIfApplied(int jobId,String username);
}
