package com.historyarchive.archivedocuments.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.historyarchive.archivedocuments.model.User;
import com.historyarchive.archivedocuments.services.UsersService;

@Controller
public class LoginController {
	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/registration")
	public String registrate(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping("/loggedout")
	public String logOut() {
		return "loggedout";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {
		
		if (result.hasErrors()) {
			return "registration";
		}
		
		if (usersService.exists(user.getPassport())) {
			// register error of the duplication and match it with a message
			// from messages.properties file
			result.rejectValue("passport", "DuplicateKey.user.passport");
			return "registration";
		}
		
		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("passport", "DuplicateKey.user.passport");
			return "registration";
		}
		
		return "registrated";
	}
}