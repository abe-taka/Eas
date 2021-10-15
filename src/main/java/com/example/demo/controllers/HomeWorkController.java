package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//宿題
@Controller
public class HomeWorkController {

	// 宿題
	@GetMapping(value = "/homework")
	public String Homework(Model model) {
			return "homework/homework";
	}

	// 宿題提出
	@GetMapping(value = "/homeworksubmi")
	public String HomeworkSubmission() {
		return "homework/homeworksubmi";
	}

	// 提出状況
	@GetMapping(value = "/homework_submistatus")
	public String HomeworkSubmissionStatus() {
		return "homework/homework_submistatus";
	}
}