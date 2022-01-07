package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TransferwithdrawalEntity;

//時間割
@Repository
public interface TransferwithdrawalRepository extends JpaRepository<TransferwithdrawalEntity, Integer>{

	
	
}