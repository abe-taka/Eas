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
		private StudentEntity teacher;

		//種別（転校か退学判断するための値）
		@Column(name = "transfer_withdrawal_log")
		private String category;
		
		//現在の時刻
		@Column(name = "execution_datetime")
		private Date execution_datetime;
			


}
	
	

	