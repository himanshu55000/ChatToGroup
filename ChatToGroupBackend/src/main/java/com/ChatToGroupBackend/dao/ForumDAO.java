package com.ChatToGroupBackend.dao;

import java.util.List;

import com.ChatToGroupBackend.model.ForumComment;
import com.ChatToGroupBackend.model.Forum;

public interface ForumDAO {
	boolean insertOrUpdateForum(Forum forum);
	List<Forum> getForums();
	Forum getForumById(int id);
/*	List<Forum> getForumsByUser(String username);
*/	boolean deleteForum(Forum forum);
	boolean addForumComment(ForumComment forumComment);
	List<ForumComment> getAllForumComment(int forum_id);
}
