package com.example.demo.database.yyyy_sn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long> 
{

}
