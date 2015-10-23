package com.javachen.service;

import com.javachen.domain.User;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

@Component
public class UserService {
	
	public User getBillGates() {
		return new User("Gates", "Bill", new GregorianCalendar(1955, 9, 28), true);
	}
	
	public User getSteveJobs() {
		return new User("Jobs", "Steve", new GregorianCalendar(1955, 2, 24), true);
	}
}
