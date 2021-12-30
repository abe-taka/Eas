package com.example.demo.sendsockets;

//授業内問題解答
public class SendAnswer {

	// 学生名
	private String studentname;

	// 出席番号
	private String class_no;

	// 解答
	private String answer;
	
	//問題番号
    private String issue_num;

	// コンストラクタとオーバーロード
	public SendAnswer() {
	}

	public SendAnswer(String studentname,String class_no,String answer,String issue_num) {
	        this.studentname = studentname;
	        this.class_no = class_no;
	        this.answer = answer;
	        this.issue_num = issue_num;
	}

	// ゲッター、セッター
	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getIssue_num() {
		return issue_num;
	}

	public void setIssue_num(String issue_num) {
		this.issue_num = issue_num;
	}
	
}