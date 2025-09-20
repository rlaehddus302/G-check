package com.example.demo.database.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> 
{
	
}
