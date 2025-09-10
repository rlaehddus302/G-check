package com.example.demo.database.graduation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralEducationRepository extends JpaRepository<GeneralEducation, Long> 
{

}
