package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepositories.TimetabletimeCustomRepository;
import com.example.demo.entities.SchoolEntity;
import com.example.demo.entities.TimetabletimeEntity;

//時間割
@Repository
public interface TimetabletimeRepository extends JpaRepository<TimetabletimeEntity, Integer>, TimetabletimeCustomRepository {

	public List<TimetabletimeEntity> findBySchool( Optional<SchoolEntity> listschool); 
	
	@Query(value = "SELECT * FROM timetabletime_table WHERE timetable_id=:timetable_id", nativeQuery = true)
	 public TimetabletimeEntity findByQuery(@Param("timetable_id") int timetable_id);
	
	//public TimetabletimeEntity findByTimetable_id(int timedata); 
	
}