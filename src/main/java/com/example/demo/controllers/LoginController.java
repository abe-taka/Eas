package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//ログイン
@Controller
public class LoginController {

	//ログイン
	@GetMapping("/")
	public String getLoginPage(Model model) {
	    return "/login/login";
	}

	// 新規登録画面
	@GetMapping(value = "/signup")
	public String Signup(Model model) {
		return "redirect:home/home";
	}
}