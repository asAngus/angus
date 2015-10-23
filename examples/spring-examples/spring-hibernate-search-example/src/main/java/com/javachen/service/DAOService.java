package com.javachen.service;

import com.javachen.entities.UserEntity;

public interface DAOService {

	UserEntity getUser(int id);
	
	UserEntity findUserByFirstName(String firstName);
}