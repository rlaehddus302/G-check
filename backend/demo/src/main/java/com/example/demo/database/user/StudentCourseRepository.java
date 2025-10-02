package com.example.demo.database.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> 
{
    @Query("""
            select studentCourse
            from StudentCourse studentCourse
            where studentCourse.stAcademicTerm.student.id = :studentID
            and studentCourse.graduationStandard.id = :GradStandID
        """)
	List<StudentCourse> findbyStudentIdNGradStandId(Long studentID, Long GradStandID);
    
    @Query("""
            select studentCourse
            from StudentCourse studentCourse
            where studentCourse.stAcademicTerm.student.id = :studentID
        """)
	List<StudentCourse> findbyStudentID(Long studentID);
}
