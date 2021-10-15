package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//授業
@Controller
public class ClassController {

	// 授業選択
	@GetMapping(value = "/roomselect")
	public String RoomSelect(Model model) {
			return "class/roomselect";
	}

	// 授業ページ
	@GetMapping(value = "/class")
	public String Class() {
		return "class/class";
	}
}