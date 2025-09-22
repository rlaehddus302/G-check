package com.example.demo.database.graduation.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.database.yyyy_sn.Course;

public interface GradCourseRepository extends JpaRepository<GradCourse, Long> 
{
    @Query("""
            select gradCourse
            from GradCourse gradCourse
            join fetch gradCourse.standardCourses standardCourse
            join fetch standardCourse.graduationStandard graduationStandard
            where graduationStandard.id = :id
              and gradCourse.name = :name
        """)
    Optional<GradCourse> findByGradStNCourse( @Param("name") String name,  @Param("id") Long gradStID);
}
