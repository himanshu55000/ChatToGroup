package com.ChatToGroupBackend.dao;


import java.util.List;

import com.ChatToGroupBackend.model.Friends;
import com.ChatToGroupBackend.model.UserDetails;


public interface FriendsDAO {
	List<UserDetails> getSuggestedFriends(String username);
	boolean addOrUpdateFriend(Friends friend);
	public List<UserDetails> getFriendRequests(String username);
	public List<UserDetails> getFriendsList(String username);
	public Friends getFriend(String toId,String fromId);
	public List<UserDetails> getSentRequests(String username);
}
