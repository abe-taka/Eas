package com.example.demo.rests;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAdminController {
	
	@PostMapping("/rest/return_transfer")
	public String transfer(@RequestParam("name") String name, @RequestParam("school_year") String school_year,@RequestParam("school_class") String school_class,@RequestParam("categoraiz") String categoraiz) {
		
		//名前の取得
		System.out.println(name);
		//学年の取得
		System.out.println(school_year);
		//クラスの取得
		System.out.println(school_class);
		//退学か転校かの判別の為の値の取得
		System.out.println(categoraiz);
		
		//登録した日の日時を取得
		Date date = new Date();
		System.out.println(date);
		
		
		return "0";
		}
	
	@PostMapping("/rest/return_jsonsend")
	public String jsonsend(@RequestParam("year_student") String year_student,@RequestParam("class_student") String class_student) {
		
		System.out.println(year_student);
		System.out.println(class_student);
		
		return "1";
	}
	

}
