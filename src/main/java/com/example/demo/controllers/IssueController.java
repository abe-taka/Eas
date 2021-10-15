package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//問題
@Controller
public class IssueController {
	
	// 問題作成
	@GetMapping(value = "/issuecreate")
	public String IssueCreate(Model model) {
		return "issue/issuecreate";
	}

	// 問題管理
	@GetMapping(value = "/issuemanage")
	public String IssueManage() {
		return "issue/issuemanage";
	}
}
