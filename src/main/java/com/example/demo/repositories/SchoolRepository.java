package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.StudentCustomRepository;
import com.example.demo.entities.ClassEntity;
import com.example.demo.entities.SchoolEntity;
import com.example.demo.entities.StudentEntity;

//学生
@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity,Integer>{
	
	public SchoolEntity findBySchoolcode(int school_code);
	

}