package com.historyarchive.archivedocuments.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.historyarchive.archivedocuments.services.ArchiveDocumentsService;

@Controller
public class MainController {
	private ArchiveDocumentsService archiveDocumentsService;

	@Autowired
	public void setAdService(ArchiveDocumentsService archiveDocumentsService) {
		this.archiveDocumentsService = archiveDocumentsService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("users", archiveDocumentsService.getUsers());
		return "index";
	}
}