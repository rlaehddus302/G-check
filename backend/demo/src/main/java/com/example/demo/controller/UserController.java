package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.graduation.GeneralEducation;
import com.example.demo.database.graduation.GraduationRequirements;
import com.example.demo.database.graduation.GraduationRequirementsRepository;
import com.example.demo.database.graduation.Major;
import com.example.demo.database.graduation.course.GradCourse;
import com.example.demo.database.graduation.course.GradCourseRepository;
import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.user.StAcademicTerm;
import com.example.demo.database.user.StAcademicTermRepository;
import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentCourse;
import com.example.demo.database.user.StudentCourseRepository;
import com.example.demo.database.user.StudentRepository;

@RestController
public class UserController 
{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	StAcademicTermRepository stAcademicTermRepository;
	@Autowired
	GraduationRequirementsRepository graduationRequirementsRepository;
	@Autowired
	GradCourseRepository gradCourseRepository;
	@Autowired
	StudentCourseRepository studentCourseRepository;
	
	@PostMapping(path = "/enrollments")
	public ResponseEntity<String> registerCourse(@RequestBody StudentCourse studentCourse)
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = authentication.getName();
		Student student = studentRepository.findByUserID(id).get();
		int year = studentCourse.getStAcademicTerm().getAcademicYear();
		String semester = studentCourse.getStAcademicTerm().getSemester();
		StAcademicTerm stAcademicTerm = stAcademicTermRepository.findByStudentAcademicYearAndSemester(year, semester, student.getId())
																.orElse(null);
		if(stAcademicTerm == null)
		{
			stAcademicTerm = new StAcademicTerm(null, year, semester, new ArrayList<StudentCourse>(), student);
			student.getStAcademicTerms().add(stAcademicTerm);
			stAcademicTermRepository.save(stAcademicTerm);
		}
		int admissionYear = student.getAdmissionYear();
		String major = student.getMajor();
		GraduationRequirements graduationRequirements = graduationRequirementsRepository.findOneByDeptAndYear(major, admissionYear).get();
		Major majorRequir = graduationRequirements.getMajor();
		List<GraduationStandard> graduationStandards = majorRequir.getGraduationStandards();
		for(GraduationStandard element : graduationStandards)
		{
			Optional<GradCourse> gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), element.getId());
			if(gradCourse.isPresent())
			{
				studentCourse.setStAcademicTerm(stAcademicTerm);
				studentCourse.setGraduationStandard(element);
				studentCourseRepository.save(studentCourse);
				return ResponseEntity.status(HttpStatus.CREATED).body("success");
			}
		}
		GeneralEducation GeneralEduRequir = graduationRequirements.getGeneralEducation();
		graduationStandards = GeneralEduRequir.getGraduationStandards();
		for(GraduationStandard element : graduationStandards)
		{
			Optional<GradCourse> gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), element.getId());
			if(gradCourse.isPresent())
			{
				studentCourse.setStAcademicTerm(stAcademicTerm);
				studentCourse.setGraduationStandard(element);
				studentCourseRepository.save(studentCourse);
				return ResponseEntity.status(HttpStatus.CREATED).body("success");
			}
		}
		studentCourse.setStAcademicTerm(stAcademicTerm);
		studentCourseRepository.save(studentCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body("success");
	}
}
