package com.javachen.service.impl;

import com.javachen.dao.UserDAO;
import com.javachen.entities.UserEntity;
import com.javachen.service.DAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("daoService")
public class DAOServiceImpl implements DAOService {
	
	@Autowired
	private UserDAO userDao;

	@Override
	public UserEntity getUser(int id) {
		return userDao.findById(id);
	}

	@Override
	public UserEntity findUserByFirstName(String firstName) {
		userDao.updateIndexes();
		List<UserEntity> results = userDao.findByWildcard("firstName", firstName);
		if (results.size() > 0) {
			return results.get(0);
		}
		
		results = userDao.findByFuzzy("firstName", firstName);
		if (results.size() > 0) {
			return results.get(0);
		}
		
		return null;
	}
}
