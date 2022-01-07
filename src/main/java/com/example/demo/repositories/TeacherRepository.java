package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.TeacherCustomRepository;
import com.example.demo.entities.TeacherEntity;
import com.example.demo.entities.TimetableEntity;

//先生
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,String>,TeacherCustomRepository{
	
	public TeacherEntity findByTeacheraddress(String teacheraddress);

}