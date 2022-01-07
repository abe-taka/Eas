package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.components.SessionManage;
import com.example.demo.entities.ClassEntity;
import com.example.demo.entities.SchoolEntity;
import com.example.demo.entities.StudentEntity;
import com.example.demo.entities.TeacherEntity;
import com.example.demo.entities.TimetableEntity;
import com.example.demo.entities.TimetabletimeEntity;
import com.example.demo.forms.AdminForm;
import com.example.demo.repositories.ClassRepository;
import com.example.demo.repositories.SchoolRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.TimetableRepository;
import com.example.demo.repositories.TimetabletimeRepository;

@Controller
public class AdministratorController {
	
	@Autowired
	 private StudentRepository studentrepository;
	@Autowired
	SessionManage session_manage;
	@Autowired
	TeacherRepository teacherrepository;
	@Autowired
	TimetableRepository timetablerepository;
	@Autowired
	TimetabletimeRepository timetabletimerepository;
	@Autowired
	SchoolRepository schoolrepository;
	@Autowired
	ClassRepository classRepository;
	
	//管理者(HOME)を表示
		@GetMapping(value = "/admin/admin_home")
		public String Admin_home(Model model) {
			
			// セッションがあるかをチェック
			if (session_manage.getSession_mail() == null) {
				return "redirect:/";
			} else {
				// メールを取得
				String session_mail = session_manage.getSession_mail();
				model.addAttribute("session_mail", session_mail);

				// 名前を取得
				String session_name = session_manage.getSession_name();
				model.addAttribute("session_name", session_name);
			
			return "admin/admin_home";
			}
		}
		
	//時間割に遷移
		@GetMapping(value = "/admin/timetable")
		public String Timetable(Model model) {
			// セッションがあるかをチェック
			if (session_manage.getSession_mail() == null) {
				return "redirect:/";
			} else {
				// メールを取得
				String session_mail = session_manage.getSession_mail();
				model.addAttribute("session_mail", session_mail);

				// 名前を取得
				String session_name = session_manage.getSession_name();
				model.addAttribute("session_name", session_name);
				
				int session_school = session_manage.getSession_schoolcode();
				
				Optional <SchoolEntity> schoolentity = schoolrepository.findById(session_school);
				
				TeacherEntity teacherentity = new TeacherEntity();
				teacherentity = teacherrepository.findByTeacheraddress(session_mail);
				List<TimetableEntity> listtimetableentity = timetablerepository.findByTeacher(teacherentity);
				model.addAttribute("teacher_timetable", listtimetableentity);
				
				List<TimetabletimeEntity> listtimeentity = timetabletimerepository.findBySchool(schoolentity);
				model.addAttribute("school_time", listtimeentity);
				
				model.addAttribute("timetableform", new AdminForm());

			return "admin/timetable";
			}
			
			
		}
		
		@PostMapping(value = "/post")
		 public String Post(AdminForm testForm) {

		  /*--科目、クラスの変更--*/
		  
		  // フォームからリストを受け取る
		  List<List<String>> list_timetable = testForm.getList_timetable();
		  System.out.println("list_timetable:" + list_timetable);

		  // 更新
		  for (int i = 0; i < list_timetable.size(); i++) {
		   // TimetableEntityオブジェクト作成
		   TimetableEntity timetableEntity = new TimetableEntity();
		   // 主キー
		   int id = Integer.parseInt(list_timetable.get(i).get(0));
		   System.out.println("id : " + id);
		   // 主キーを基に、データを取得
		   timetableEntity = timetablerepository.findById(id);

		   // 科目
		   String subject = list_timetable.get(i).get(1);
		   System.out.println("subject :" + subject);
		   // 変更データをセット
		   timetableEntity.setSubjectname(subject);

		   // クラス
		   String class_data = list_timetable.get(i).get(2);
		   System.out.println("class_data :" + class_data);
		   int year_char = 0;
	       int class_char = 0;
	       
		   // 変更クラスのデータの取得
		   for (int j = 0; j< class_data.length(); j++) {
		        if(j == 0 || j == 2) {
		         System.out.println(String.format("class_dataのインデックス %d は %s です。", j, class_data.charAt(j)));
		         if(j == 0) {
		          //学年の取得
		        	 year_char = Character.getNumericValue(class_data.charAt(j));  
		             System.out.println("year_char"+year_char);
		         }else if(j == 2) { 
		          //組の取得
		          class_char = Character.getNumericValue(class_data.charAt(j));  
		          System.out.println("class_char"+String.valueOf(class_char));
		         }
		         
		         
		        }
		        
		       }
		   
		   int school_code = session_manage.getSession_schoolcode();
	         System.out.println("school_code"+String.valueOf(school_code));
	         // 変更クラスのデータの取得(学年、組データを基に)
	         // クラスEntityオブジェクト作成
			 ClassEntity classEntity = new ClassEntity();
	         classEntity = classRepository.findByQuery(year_char, class_char, school_code);
	         System.out.println("ClassEntity id :" + classEntity.getClassid());
	         // 変更データをセット
	         timetableEntity.setClassentity(classEntity);
	         // 更新
	         timetablerepository.save(timetableEntity);
		  }

		  
		  /*--限数ごとの開始時間、終了時間の変更--*/
		  
		  // フォームからリストを受け取る
		  List<List<String>> list_periodtime = testForm.getList_periodtime();
		  System.out.println("list_periodtime:" + list_periodtime);
		  
		  // 更新
		  for (int i = 0; i < list_periodtime.size(); i++) {
		   // TimetabletimeEntityオブジェクト作成
		   TimetabletimeEntity timetabletimeEntity = new TimetabletimeEntity();
		   // 主キー
		   int id = Integer.parseInt(list_periodtime.get(i).get(0));
		   // 主キーを基に、データを取得
		   timetabletimeEntity = timetabletimerepository.findByQuery(id);
		   
		   //開始時間
		   String start_time = list_periodtime.get(i).get(1);
		   // 変更データをセット
		   timetabletimeEntity.setStarttime(start_time);
		   //開始時間
		   String end_time = list_periodtime.get(i).get(2);
		   // 変更データをセット
		   timetabletimeEntity.setEndtime(end_time);
		   
		   // 更新
		   timetabletimerepository.save(timetabletimeEntity);
		  }
		  return "redirect:/admin/transferschool_dropoutprocess";
		 }
		
	//転校・退学処理画面に遷移
		@GetMapping(value = "/admin/transferschool_dropoutprocess")
		public String TransferSchool_DropoutProcess(Model model) {
			
			// セッションがあるかをチェック
			if (session_manage.getSession_mail() == null) {
				return "redirect:/";
			} else {
				// メールを取得
				String session_mail = session_manage.getSession_mail();
				model.addAttribute("session_mail", session_mail);

				// 名前を取得
				String session_name = session_manage.getSession_name();
				model.addAttribute("session_name", session_name);
				
				//
				int session_school = session_manage.getSession_schoolcode();
			
			
			List<StudentEntity> student_pull = studentrepository.findByQuery(1, 1, session_school);
	        model.addAttribute("student_pull", student_pull);
	        // プルダウンの初期値を設定する場合は指定
	        model.addAttribute("selectedValue", "01");
			return "/admin/transferschool_dropoutprocess";
			}
		}
		
		

		 
	
}
