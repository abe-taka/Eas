package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

//転校退学ログテーブル
@Entity
@Table(name = "transfer_withdrawal_log")
public class TransferwithdrawalEntity {

		// ログID
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "log_id")
		private int log_id;
	
		// 生徒アドレス
		//@Many ToOne 外部キーを表してる
		@ManyToOne
		//@JoinColum 外部キー版のカラム
		@JoinColumn(name = "student_address")
		private StudentEntity student;
		
		// 先生アドレス
		//@Many ToOne 外部キーを表してる
		@ManyToOne
		//@JoinColum 外部キー版のカラム
		@JoinColumn(name = "teacher_address")
		private TeacherEntity teacher;

		//種別（転校か退学判断するための値）
		@Column(name = "transfer_withdrawal_log")
		private String category;
		
		//現在の時刻
		@Column(name = "execution_datetime")
		private Date execution_datetime;

		public int getLog_id() {
			return log_id;
		}

		public void setLog_id(int log_id) {
			this.log_id = log_id;
		}

		public StudentEntity getStudent() {
			return student;
		}

		public void setStudent(StudentEntity student) {
			this.student = student;
		}

		public TeacherEntity getTeacher() {
			return teacher;
		}

		public void setTeacher(TeacherEntity teacher) {
			this.teacher = teacher;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Date getExecution_datetime() {
			return execution_datetime;
		}

		public void setExecution_datetime(Date execution_datetime) {
			this.execution_datetime = execution_datetime;
		}

}
	
	

	