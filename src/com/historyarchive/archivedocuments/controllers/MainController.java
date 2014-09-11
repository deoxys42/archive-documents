package com.historyarchive.archivedocuments.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.historyarchive.archivedocuments.services.UsersService;

@Controller
public class MainController {
	private UsersService usersService;

	@Autowired
	public void setAdService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("users", usersService.getUsers());
		return "index";
	}
}