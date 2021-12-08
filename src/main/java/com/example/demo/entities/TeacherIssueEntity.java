package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_question_tbl")
public class TeacherIssueEntity {

	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_question_id")
	private int id;
	
	//先生のメールアドレス
	@ManyToOne
	@JoinColumn(name="teacher_address")
	private TeacherEntity teacher;
	
	//問題文
	@Column(name="question_text")
	private String questiontext;
	
	//解答
	@Column(name="model_answer")
	private String answer;
	
	//曜日
	@Column(name="dayofweek")
	private String dayofweek;
	
	//時間
	@Column(name="question_time")
	private String question_time;

	//ゲッター、セッター
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public String getQuestiontext() {
		return questiontext;
	}

	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getQuestion_time() {
		return question_time;
	}

	public void setQuestion_time(String question_time) {
		this.question_time = question_time;
	}
	
}
