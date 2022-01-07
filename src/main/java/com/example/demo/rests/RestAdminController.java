package com.example.demo.rests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.JsonConversion;
import com.example.demo.components.SessionManage;
import com.example.demo.entities.StudentEntity;
import com.example.demo.repositories.StudentRepository;

@RestController
public class RestAdminController {
	
	@Autowired
	SessionManage session_manage;
	@Autowired
	JsonConversion json;
	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/rest/return_transfer")
	public String transfer(@RequestParam("name") String name, @RequestParam("school_year") String school_year,@RequestParam("school_class") String school_class, @RequestParam("categoraiz") String categoraiz) {

		// 名前の取得
		System.out.println(name);
		// 学年の取得
		System.out.println(school_year);
		// クラスの取得
		System.out.println(school_class);
		// 退学か転校かの判別の為の値の取得
		System.out.println(categoraiz);

		// 登録した日の日時を取得
		Date date = new Date();
		System.out.println(date);

		return "0";
	}

	@GetMapping("/rest/return_jsonsend")
	public List<StudentEntity> jsonsend(@RequestParam("year_student") String year_student,@RequestParam("class_student") String class_student) {

		System.out.println("year_student" + year_student);
		System.out.println("class_student" + class_student);
		
		int school_year = 0;
		int school_class = 0;

		// 学年を取得
		for (int j = 0; j < year_student.length(); j++) {
			if (j == 0) {
				// 学年の取得
				school_year = Character.getNumericValue(year_student.charAt(j));
				System.out.println("school_year" + school_year);
			}
		}

		// 組を取得
		for (int j = 0; j < class_student.length(); j++) {
			if (j == 0) {
				// 学年の取得
				school_class = Character.getNumericValue(class_student.charAt(j));
				System.out.println("school_class" + school_class);
			}
		}
		
		//学校コードを取得
		int school_code = session_manage.getSession_schoolcode();
		
		//学生を取得
		List<StudentEntity> list_studentEntity = studentRepository.findByQuery(school_year, school_class, school_code);
		
		return list_studentEntity;
	}
}
