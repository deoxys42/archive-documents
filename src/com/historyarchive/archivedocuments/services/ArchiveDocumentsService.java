package com.historyarchive.archivedocuments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.historyarchive.archivedocuments.model.User;
import com.historyarchive.archivedocuments.model.daos.UsersDao;

@Service
public class ArchiveDocumentsService {
	private UsersDao usersDao;

	public UsersDao getUsersDao() {
		return usersDao;
	}
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public List<User> getUsers() {
		return usersDao.getAll();
	}
}
