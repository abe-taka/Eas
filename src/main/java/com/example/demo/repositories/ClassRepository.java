package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.ClassCustomRepository;
import com.example.demo.entities.ClassEntity;
import com.example.demo.entities.SchoolEntity;

//クラス
@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,String>,ClassCustomRepository{

	@Query(value = "SELECT DISTINCT school_year FROM class_table WHERE school_code=:school_code", nativeQuery = true)
	public List<String> findBySchoolyear(@Param("school_code") int school_code);
	
	public ClassEntity findByClassid(String classdata); 
	
	public ClassEntity findBySchoolyearAndSchoolclassAndSchool(int schoolyear, int schoolclass, SchoolEntity school);
	
	@Query(value = "SELECT * FROM class_table WHERE school_year=:school_year AND school_class=:school_class AND school_code=:school_code", nativeQuery = true)
	 public ClassEntity findByQuery(@Param("school_year") int school_year,@Param("school_class") int school_class,@Param("school_code") int school_code);
}