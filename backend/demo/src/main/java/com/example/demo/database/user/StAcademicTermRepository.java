package com.example.demo.database.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StAcademicTermRepository extends JpaRepository<StAcademicTerm, Long> 
{
    @Query("""
            select Stat
            from StAcademicTerm Stat
            join fetch Stat.student st
            join fetch Stat.studentCourses courses
            where Stat.semester = :semester
              and Stat.academicYear = :year
              and st.id = :id
        """)
	Optional<StAcademicTerm> findByStudentAcademicYearAndSemester(int year, String semester, Long id);
}
