package com.example.demo.database.graduation.graduationStandard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.database.graduation.GraduationRequirements;

@Repository
public interface GraduationStandardRepository extends JpaRepository<GraduationStandard, Long> 
{
    @Query("""
            select gs
            from GraduationStandard gs
            left join fetch gs.studentCourse studentCourse
            where gs.major.id = :majorID
        """)
        List<GraduationStandard> findBymjaorID(@Param("majorID") Long majorID);
    
    @Query("""
            select gs
            from GraduationStandard gs
            left join fetch gs.studentCourse studentCourse
            where gs.generalEducation.id = :generalEducationID
        """)
        List<GraduationStandard> findByGeneralEducationID(@Param("generalEducationID") Long generalEducationID);
    
    @Query("""
            select gs
            from GraduationStandard gs
            join fetch gs.standardCourses standardCourses
            join fetch standardCourses.gradCourse
            where gs.parent.id = :parentID
        """)
        List<GraduationStandard> findByParentID(@Param("parentID") Long parentID);
}
