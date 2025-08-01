package com.example.demo.database.department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
	
	List<Department> findByDepartmentContaining(String keyword);
}
