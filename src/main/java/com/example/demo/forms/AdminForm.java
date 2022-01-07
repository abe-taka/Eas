package com.example.demo.forms;

import java.util.ArrayList;
import java.util.List;

public class AdminForm {
	
	//主キー、科目、クラスを受け取るリスト
	 private List<List<String>> list_timetable = new ArrayList<List<String>>();
	 
	 //限数ごとの開始時間、終了時間を受け取るリスト
	 private List<List<String>> list_periodtime = new ArrayList<List<String>>();

	 public List<List<String>> getList_timetable() {
	  return list_timetable;
	 }

	 public void setList_timetable(List<List<String>> list_timetable) {
	  this.list_timetable = list_timetable;
	 }

	 public List<List<String>> getList_periodtime() {
	  return list_periodtime;
	 }

	 public void setList_periodtime(List<List<String>> list_periodtime) {
	  this.list_periodtime = list_periodtime;
	 }
	
}
