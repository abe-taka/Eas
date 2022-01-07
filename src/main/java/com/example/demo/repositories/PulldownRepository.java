package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.StudentEntity;

/**
 * 生徒情報 Repository
 */
@Repository

public interface PulldownRepository  extends JpaRepository<StudentEntity, Long> {
	

}
