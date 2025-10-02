package com.example.demo.database.graduation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduationRequirementsRepository extends JpaRepository<GraduationRequirements, Long> {

    @Query("""
            select gr
            from GraduationRequirements gr
            join fetch gr.major major
            join fetch gr.generalEducation generalEducation
            where gr.academicYear.department.department = :deptName
              and gr.academicYear.academicYear = :year
        """)
        Optional<GraduationRequirements> findOneByDeptAndYear(@Param("deptName") String departmentName,@Param("year") int academicYear);
}
