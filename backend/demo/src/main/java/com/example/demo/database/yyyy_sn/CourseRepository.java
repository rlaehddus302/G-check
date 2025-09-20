package com.example.demo.database.yyyy_sn;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CourseRepository extends JpaRepository<Course, Long> 
{
    @Query("""
            select course
            from Course course
            join fetch course.academicTerm at
            where at.semester = :semester
              and at.academicYear = :year
              and course.name like lower(concat('%', :name, '%'))
        """)
    List<Course> findByAcademicTerm(@Param("year") int academicYear, @Param("semester") String semester, @Param("name") String name);
}
