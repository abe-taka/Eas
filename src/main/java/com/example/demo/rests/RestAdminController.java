package com.example.demo.rests;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.JsonConversion;
import com.example.demo.components.SessionManage;
import com.example.demo.entities.ClassEntity;
import com.example.demo.entities.SchoolEntity;
import com.example.demo.entities.StudentEntity;
import com.example.demo.entities.TeacherEntity;
import com.example.demo.entities.TransferwithdrawalEntity;
import com.example.demo.repositories.ClassRepository;
import com.example.demo.repositories.SchoolRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.TransferwithdrawalRepository;

@RestController
public class RestAdminController {
	
	@Autowired
	SessionManage session_manage;
	@Autowired
	JsonConversion json;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherrepository;
	@Autowired
	TransferwithdrawalRepository transferwithdrawalRepository;
	@Autowired
	ClassRepository classrepository;
	@Autowired
	SchoolRepository schoolrepository;

	@PostMapping("/rest/return_transfer")
	public String transfer(@RequestParam("name") String name, @RequestParam("school_year") String school_year,@RequestParam("school_class") String school_class, @RequestParam("categoraiz") String categoraiz) {
		
		//先生のメールアドレスを取得
		String session_mail = session_manage.getSession_mail();
		System.out.println(session_mail);
		//学校コード
		int school_code = session_manage.getSession_schoolcode();
		System.out.println(school_code);
		// 名前を取得
		String session_name = session_manage.getSession_name();
		System.out.println(session_name);
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
		
		int year_school = 0;
		int class_school = 0;
		
		// 学年の取得
		year_school = Character.getNumericValue(school_year.charAt(0));
		System.out.println("year_school" + year_school);
		
		// 組を取得
		class_school = Character.getNumericValue(school_class.charAt(0));
		System.out.println("class_school" + class_school);
		
		//jsから取得してきたnameから名前のみ取得
		String no = "番";
		int beginIndex = name.indexOf(no); //番の位置を取得
		String named = name.substring(beginIndex +1);
		System.out.println(named); // → "名前"
		
		//jsから取得してきたnameから出席番号のみ取得
		int intStr = Integer.parseInt(name.replaceAll("[^0-9]",  ""));
		System.out.println(intStr);
		
		StudentEntity studententity = new StudentEntity();
		SchoolEntity schoolEntity = new SchoolEntity();
		schoolEntity = schoolrepository.findBySchoolcode(school_code);
		ClassEntity classEntity = new ClassEntity();
		classEntity = classrepository.findBySchoolyearAndSchoolclassAndSchool(year_school, class_school, schoolEntity);
		studententity = studentRepository.findByClassentityAndClassnoAndStudentname(classEntity, intStr, named);
		
		//
		TeacherEntity teacherentity = new TeacherEntity();
		teacherentity = teacherrepository.findByTeacheraddress(session_mail);
		
		TransferwithdrawalEntity transferwithdrawalentity = new TransferwithdrawalEntity();
		//データをセット
		transferwithdrawalentity.setCategory(categoraiz);
		transferwithdrawalentity.setExecution_datetime(date);
		transferwithdrawalentity.setStudent(studententity);
		transferwithdrawalentity.setTeacher(teacherentity);
		
		transferwithdrawalRepository.save(transferwithdrawalentity);
		
		
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
