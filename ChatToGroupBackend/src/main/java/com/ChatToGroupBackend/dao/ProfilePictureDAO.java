package com.ChatToGroupBackend.dao;

import com.ChatToGroupBackend.model.ProfilePicture;

public interface ProfilePictureDAO {
	public void insertOrUpdateProfilePicture(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String username);
}
