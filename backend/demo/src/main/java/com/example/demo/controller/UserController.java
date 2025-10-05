package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.Condition;
import com.example.demo.database.graduation.GeneralEducation;
import com.example.demo.database.graduation.GraduationRequirements;
import com.example.demo.database.graduation.GraduationRequirementsRepository;
import com.example.demo.database.graduation.Major;
import com.example.demo.database.graduation.course.GradCourse;
import com.example.demo.database.graduation.course.GradCourseRepository;
import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.graduation.graduationStandard.GraduationStandardRepository;
import com.example.demo.database.graduation.standardCourse.StandardCourse;
import com.example.demo.database.manyTmany.StudentCourse_GraduationStandard;
import com.example.demo.database.manyTmany.StudentCourse_GraduationStandardRepository;
import com.example.demo.database.user.StAcademicTerm;
import com.example.demo.database.user.StAcademicTermRepository;
import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentCourse;
import com.example.demo.database.user.StudentCourseRepository;
import com.example.demo.database.user.StudentRepository;
import com.example.demo.dto.CourseInfoDTO;
import com.example.demo.dto.DetailedCompletionRequirements;
import com.example.demo.dto.GraduationCheck;
import com.example.demo.dto.RequirementCourseOption;
import com.example.demo.dto.RequirementProgress;
import com.example.demo.error.DuplicateException;

@RestController
public class UserController 
{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StAcademicTermRepository stAcademicTermRepository;
	@Autowired
	private GraduationRequirementsRepository graduationRequirementsRepository;
	@Autowired
	private GradCourseRepository gradCourseRepository;
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	@Autowired
	private GraduationStandardRepository graduationStandardRepository;
	@Autowired
	private StudentCourse_GraduationStandardRepository studentCourse_graduationStandardRepository;
	
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping(path = "/user/enrollments")
	public ResponseEntity<CourseInfoDTO> registerCourse(@RequestBody StudentCourse studentCourse)
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
		else
		{
			List<StudentCourse> studentCourses = stAcademicTerm.getStudentCourses();
			String courseName = studentCourse.getName();
			for(StudentCourse element : studentCourses)
			{
				if(element.getName().equals(courseName))
				{
					throw new DuplicateException("이미 있는 교과목입니다.");
				}
			}
		}
		int admissionYear = student.getAdmissionYear();
		String major = student.getMajor();
		GraduationRequirements graduationRequirements = graduationRequirementsRepository.findOneByDeptAndYear(major, admissionYear).get();
		Major majorRequir = graduationRequirements.getMajor();
		List<GraduationStandard> graduationStandards = graduationStandardRepository.findBymjaorID(majorRequir.getId());
		for(GraduationStandard element : graduationStandards)
		{
			Optional<GradCourse> gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), element.getId());
			if(gradCourse.isPresent())
			{
				
				studentCourse.setStAcademicTerm(stAcademicTerm);
				studentCourse.setCategory(element.getCategory());
				StudentCourse saved = studentCourseRepository.save(studentCourse);
				CourseInfoDTO infoDto = new CourseInfoDTO(saved.getId(),studentCourse.getName(), studentCourse.getCredit(), studentCourse.getScore(),studentCourse.getLanguage(), element.getCategory());
				studentCourse_graduationStandardRepository.save(new StudentCourse_GraduationStandard(null, element, saved));
				List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
				for(GraduationStandard child : children)
				{
					gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), child.getId());
					if(gradCourse.isPresent())
					{
						studentCourse_graduationStandardRepository.save(new StudentCourse_GraduationStandard(null, child, saved));
					}
				}
				return ResponseEntity.status(HttpStatus.CREATED).body(infoDto);
			}
		}
		GeneralEducation GeneralEduRequir = graduationRequirements.getGeneralEducation();
		graduationStandards = graduationStandardRepository.findByGeneralEducationID(GeneralEduRequir.getId());
		for(GraduationStandard element : graduationStandards)
		{
			Optional<GradCourse> gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), element.getId());
			if(gradCourse.isPresent())
			{
				studentCourse.setStAcademicTerm(stAcademicTerm);
				studentCourse.setCategory(element.getCategory());
				StudentCourse saved = studentCourseRepository.save(studentCourse);
				CourseInfoDTO infoDto = new CourseInfoDTO(saved.getId(), studentCourse.getName(), studentCourse.getCredit(), studentCourse.getScore(),studentCourse.getLanguage(), element.getCategory());
				studentCourse_graduationStandardRepository.save(new StudentCourse_GraduationStandard(null, element, saved));
				List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
				for(GraduationStandard child : children)
				{
					gradCourse = gradCourseRepository.findByGradStNCourse(studentCourse.getName(), child.getId());
					if(gradCourse.isPresent())
					{
						studentCourse_graduationStandardRepository.save(new StudentCourse_GraduationStandard(null, child, saved));
					}
				}
				return ResponseEntity.status(HttpStatus.CREATED).body(infoDto);
			}
		}
		studentCourse.setStAcademicTerm(stAcademicTerm);
		studentCourse.setCategory("기타");
		StudentCourse saved = studentCourseRepository.save(studentCourse);
		CourseInfoDTO infoDto = new CourseInfoDTO(saved.getId(), studentCourse.getName(), studentCourse.getCredit(), studentCourse.getScore(),studentCourse.getLanguage(), "기타");
		return ResponseEntity.status(HttpStatus.CREATED).body(infoDto);
	}
	
	@GetMapping("/user/courses")
	public ResponseEntity<List<CourseInfoDTO>> getMyCoursesByYearAndSemester(@RequestParam int year, @RequestParam String semester)
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = authentication.getName();
		Student student = studentRepository.findByUserID(id).get();
		Optional<StAcademicTerm> StAcademicTerm = stAcademicTermRepository.findByStudentAcademicYearAndSemester(year, semester, student.getId());
		if(StAcademicTerm.isEmpty())
		{
			return ResponseEntity.ok(new ArrayList<CourseInfoDTO>());
		}
		List<StudentCourse> studentCourses = StAcademicTerm.get().getStudentCourses();
		List<CourseInfoDTO> courseInfoDTOs = new ArrayList<CourseInfoDTO>();
		for(StudentCourse element : studentCourses)
		{
			CourseInfoDTO courseInfoDTO = new CourseInfoDTO(element.getId(), element.getName(), element.getCredit(), element.getScore(), element.getLanguage(), element.getCategory());
			courseInfoDTOs.add(courseInfoDTO);
		}
		return ResponseEntity.ok(courseInfoDTOs);
	}
	
	@DeleteMapping("/user/course")
	public ResponseEntity<String> deleteCourse(@RequestParam Long courseID)
	{
		studentCourseRepository.deleteById(courseID);
		return ResponseEntity.ok("성공");
	}
	
	@GetMapping("/user/totalProgress")
	public ResponseEntity<Integer> getTotalProgress()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = authentication.getName();
		Student student = studentRepository.findByUserID(id).get();
		String major = student.getMajor();
		int admissionYear = student.getAdmissionYear();
		GraduationRequirements graduationRequirements = graduationRequirementsRepository.findOneByDeptAndYear(major, admissionYear).get();
		Major majorRequir = graduationRequirements.getMajor();
		List<GraduationStandard> graduationStandards = graduationStandardRepository.findBymjaorID(majorRequir.getId());
		int restCredit = 0;
		for(GraduationStandard element : graduationStandards)
		{
			int essentialCredit = 0;
			List<StudentCourse> studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), element.getId());
			int slectedCredit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
			int categoryRestCredit = element.getNumber() - slectedCredit;
			List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
			for(GraduationStandard child : children)
			{
				studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), child.getId());
				Condition condition = child.getCondition();
				if(condition == Condition.ALL)
				{
					if(child.getStandardCourses().size() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getStandardCourses().size() - studentCourses.size()) * 3;
					}
				}
				else if(condition == Condition.K_OF)
				{
					if(child.getNumber() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getNumber() - studentCourses.size()) * 3;
					}
				}
				else if(condition == Condition.MIN_CREDIT)
				{
					int credit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
					if(child.getNumber() > credit)
					{
						essentialCredit = essentialCredit + (child.getNumber() - credit) * 3;
					}
				}
			}
			if(essentialCredit == 0)
			{
				restCredit = restCredit + categoryRestCredit;
			}
			else if(categoryRestCredit > essentialCredit)
			{
				restCredit = restCredit + categoryRestCredit;
			}
			else
			{
				restCredit = restCredit + essentialCredit;
			}
		}
		GeneralEducation GeneralEduRequir = graduationRequirements.getGeneralEducation();
		graduationStandards = graduationStandardRepository.findByGeneralEducationID(GeneralEduRequir.getId());
		for(GraduationStandard element : graduationStandards)
		{
			int essentialCredit = 0;
			List<StudentCourse> studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), element.getId());
			int slectedCredit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
			int categoryRestCredit = element.getNumber() - slectedCredit;
			List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
			for(GraduationStandard child : children)
			{
				studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), child.getId());
				Condition condition = child.getCondition();
				if(condition == Condition.ALL)
				{
					if(child.getStandardCourses().size() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getStandardCourses().size() - studentCourses.size()) * 3;
					}
				}
				else if(condition == Condition.K_OF)
				{
					if(child.getNumber() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getNumber() - studentCourses.size()) * 3;
					}
				}
				else if(condition == Condition.MIN_CREDIT)
				{
					int credit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
					if(child.getNumber() > credit)
					{
						essentialCredit = essentialCredit + (child.getNumber() - credit) * 3;
					}
				}
			}
			if(essentialCredit == 0)
			{
				restCredit = restCredit + categoryRestCredit;
			}
			else if(categoryRestCredit > essentialCredit)
			{
				restCredit = restCredit + categoryRestCredit;
			}
			else
			{
				restCredit = restCredit + essentialCredit;
			}
		}
		int otherCredit = graduationRequirements.getTotalCredit() - GeneralEduRequir.getTotalCredit() - majorRequir.getTotalCredit();
		int otherSelectedCredit = studentCourseRepository.findbyStudentIDNCategoryName(student.getId(), "기타").stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
		log.info(Integer.toString(restCredit));
		restCredit = restCredit + otherCredit - otherSelectedCredit;
		if(restCredit < 0)
		{
			restCredit = 0;
		}
		int answer = (int)((double)(graduationRequirements.getTotalCredit() - restCredit) / graduationRequirements.getTotalCredit() * 100);
		return ResponseEntity.ok(answer);
	}
	
	@GetMapping("/user/graduationCheck")
	public ResponseEntity<GraduationCheck> getGraduationCheck()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = authentication.getName();
		Student student = studentRepository.findByUserID(id).get();
		String major = student.getMajor();
		int admissionYear = student.getAdmissionYear();
		GraduationRequirements graduationRequirements = graduationRequirementsRepository.findOneByDeptAndYear(major, admissionYear).get();
		Major majorRequir = graduationRequirements.getMajor();
		List<GraduationStandard> graduationStandards = graduationStandardRepository.findBymjaorID(majorRequir.getId());
		int restCredit = 0;
		List<RequirementProgress> RequirementProgresses = new ArrayList<RequirementProgress>();
		for(GraduationStandard element : graduationStandards)
		{
			int essentialCredit = 0;
			List<StudentCourse> outerStudentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), element.getId());
			int slectedCredit = outerStudentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
			int categoryRestCredit = element.getNumber() - slectedCredit;
			List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
			List<DetailedCompletionRequirements> detailedCompletionRequirements = new ArrayList<DetailedCompletionRequirements>();
			for(GraduationStandard child : children)
			{
				List<RequirementCourseOption> requirementCourseOptions = new ArrayList<RequirementCourseOption>();
				List<StudentCourse> studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), child.getId());
				child.getStandardCourses().stream().forEach(t -> 
				{
					boolean status = studentCourses.stream().anyMatch(k -> k.getName().equals(t.getCourse().getName()));
					requirementCourseOptions.add(new RequirementCourseOption(t.getCourse().getName(), t.getCourse().getCredit(), status));
				});
				Condition condition = child.getCondition();
				DetailedCompletionRequirements detailedCompletionRequirement;
				if(condition == Condition.ALL)
				{
					if(child.getStandardCourses().size() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getStandardCourses().size() - studentCourses.size()) * 3;
						int progress = (int)((double)(studentCourses.size()) / child.getStandardCourses().size() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
				else if(condition == Condition.K_OF)
				{
					if(child.getNumber() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getNumber() - studentCourses.size()) * 3;
						int progress = (int)((double)(studentCourses.size()) / child.getNumber() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
				else if(condition == Condition.MIN_CREDIT)
				{
					int credit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
					if(child.getNumber() > credit)
					{
						essentialCredit = essentialCredit + (child.getNumber() - credit) * 3;
						int progress = (int)((double)(credit) / child.getNumber() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
			}
			int progress;
			int selectCredit;
			if(essentialCredit == 0)
			{
				selectCredit = element.getNumber() - categoryRestCredit;
				progress =  (int)((double)(element.getNumber() - categoryRestCredit) / element.getNumber() * 100);
				if(progress > 100)
				{
					progress = 100;
				}
				restCredit = restCredit + categoryRestCredit;
			}
			else if(categoryRestCredit > essentialCredit)
			{
				selectCredit = element.getNumber() - categoryRestCredit;
				progress =  (int)((double)(element.getNumber() - categoryRestCredit) / element.getNumber() * 100);
				restCredit = restCredit + categoryRestCredit;
			}
			else
			{
				selectCredit = element.getNumber() - essentialCredit;
				progress =  (int)((double)(element.getNumber() - essentialCredit) / element.getNumber() * 100);
				restCredit = restCredit + essentialCredit;
			}
			RequirementProgress requirementProgress = new RequirementProgress(element.getCategory(), selectCredit, 
					element.getNumber(), progress, detailedCompletionRequirements);
			RequirementProgresses.add(requirementProgress);
		}
		GeneralEducation GeneralEduRequir = graduationRequirements.getGeneralEducation();
		graduationStandards = graduationStandardRepository.findByGeneralEducationID(GeneralEduRequir.getId());
		for(GraduationStandard element : graduationStandards)
		{
			int essentialCredit = 0;
			List<StudentCourse> outerStudentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), element.getId());
			int slectedCredit = outerStudentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
			int categoryRestCredit = element.getNumber() - slectedCredit;
			List<GraduationStandard> children = graduationStandardRepository.findByParentID(element.getId());
			List<DetailedCompletionRequirements> detailedCompletionRequirements = new ArrayList<DetailedCompletionRequirements>();
			for(GraduationStandard child : children)
			{
				List<RequirementCourseOption> requirementCourseOptions = new ArrayList<RequirementCourseOption>();
				List<StudentCourse> studentCourses = studentCourseRepository.findbyStudentIdNGradStandId(student.getId(), child.getId());
				child.getStandardCourses().stream().forEach(t -> 
				{
					boolean status = studentCourses.stream().anyMatch(k -> k.getName().equals(t.getCourse().getName()));
					requirementCourseOptions.add(new RequirementCourseOption(t.getCourse().getName(), t.getCourse().getCredit(), status));
				});
				Condition condition = child.getCondition();
				DetailedCompletionRequirements detailedCompletionRequirement;
				if(condition == Condition.ALL)
				{
					if(child.getStandardCourses().size() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getStandardCourses().size() - studentCourses.size()) * 3;
						int progress = (int)((double)(studentCourses.size()) / child.getStandardCourses().size() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
				else if(condition == Condition.K_OF)
				{
					if(child.getNumber() > studentCourses.size())
					{
						essentialCredit = essentialCredit + (child.getNumber() - studentCourses.size()) * 3;
						int progress = (int)((double)(studentCourses.size()) / child.getNumber() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
				else if(condition == Condition.MIN_CREDIT)
				{
					int credit = studentCourses.stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
					if(child.getNumber() > credit)
					{
						essentialCredit = essentialCredit + (child.getNumber() - credit) * 3;
						int progress = (int)((double)(credit) / child.getNumber() * 100);
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), progress, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					else
					{
						detailedCompletionRequirement = new DetailedCompletionRequirements(child.getCategory(), 100, 
								child.getNumber(), condition, requirementCourseOptions);
					}
					detailedCompletionRequirements.add(detailedCompletionRequirement);
				}
			}
			int progress;
			int selectCredit;
			if(essentialCredit == 0)
			{
				selectCredit = element.getNumber() - categoryRestCredit;
				restCredit = restCredit + categoryRestCredit;
				progress =  (int)((double)(element.getNumber() - categoryRestCredit) / element.getNumber() * 100);
				if(progress > 100)
				{
					progress = 100;
				}
			}
			else if(categoryRestCredit > essentialCredit)
			{
				selectCredit = element.getNumber() - categoryRestCredit;
				progress =  (int)((double)(element.getNumber() - categoryRestCredit) / element.getNumber() * 100);
				restCredit = restCredit + categoryRestCredit;
			}
			else
			{
				selectCredit = element.getNumber() - essentialCredit;
				progress =  (int)((double)(element.getNumber() - essentialCredit) / element.getNumber() * 100);
				restCredit = restCredit + essentialCredit;
			}
			RequirementProgress requirementProgress = new RequirementProgress(element.getCategory(), selectCredit, 
					element.getNumber(), progress, detailedCompletionRequirements);
			RequirementProgresses.add(requirementProgress);
		}
		int otherCredit = graduationRequirements.getTotalCredit() - GeneralEduRequir.getTotalCredit() - majorRequir.getTotalCredit();
		int otherSelectedCredit = studentCourseRepository.findbyStudentIDNCategoryName(student.getId(), "기타").stream().reduce(0,(sum, sc) -> sum + sc.getCredit(), Integer::sum);
		restCredit = restCredit + otherCredit - otherSelectedCredit;
		int answer = (int)((double)(graduationRequirements.getTotalCredit() - restCredit) / graduationRequirements.getTotalCredit() * 100);
		if(answer > 100)
		{
			answer = 100;
		}
		GraduationCheck graduationCheck = new GraduationCheck(graduationRequirements.getTotalCredit(), answer, RequirementProgresses, graduationRequirements.getTotalCredit() - restCredit);
		return ResponseEntity.ok(graduationCheck);
	}

}

