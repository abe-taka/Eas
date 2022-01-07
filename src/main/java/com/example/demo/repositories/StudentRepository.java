package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.StudentCustomRepository;
import com.example.demo.entities.StudentEntity;

//学生
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer>,StudentCustomRepository{
	
	@Query(value = "SELECT * FROM student_table WHERE class_id in (SELECT class_id FROM class_table WHERE school_year = :schoolyear AND school_class = :schoolclass AND school_code = :school_code ) ", nativeQuery = true)
	 public List<StudentEntity> findByQuery(@Param("schoolyear") int schoolyear , @Param("schoolclass") int schoolclass,@Param("school_code") int school_code);

}