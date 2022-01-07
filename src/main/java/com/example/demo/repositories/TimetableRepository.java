package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.TimetableCustomRepository;
import com.example.demo.entities.TeacherEntity;
import com.example.demo.entities.TimetableEntity;
import com.example.demo.entities.TimetabletimeEntity;

//時間割
@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntity, Integer>, TimetableCustomRepository {

	//時限数の取得
	public TimetableEntity findById(int time_id);
	
	public List<TimetableEntity> findByTeacher(TeacherEntity teacherentity);
}