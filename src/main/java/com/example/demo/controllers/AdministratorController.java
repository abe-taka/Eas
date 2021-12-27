package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministratorController {
	
	//管理者(HOME)を表示
		@GetMapping(value = "/admin/admin_home")
		public String Admin_home() {
			return "admin/admin_home";
		}
		
	//時間割に遷移
		@GetMapping(value = "/admin/timetable")
		public String Timetable() {
			return "admin/timetable";
		}
		
	//転校・退学処理画面に遷移
		@GetMapping(value = "/admin/transferschool_dropoutprocess")
		public String TransferSchool_DropoutProcess() {
			return "/admin/transferschool_dropoutprocess";
		}
	
}
