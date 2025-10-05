package com.example.demo.database.manyTmany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.database.user.StudentCourse;

@Repository
public interface StudentCourse_GraduationStandardRepository extends JpaRepository<StudentCourse_GraduationStandard, Long> 
{
	
}
