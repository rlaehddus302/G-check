package com.example.demo.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.department.Department;
import com.example.demo.database.department.DepartmentRepository;
import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentRepository;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserInfo;
import com.example.demo.error.DuplicateIdException;
import com.example.demo.error.IdBlankException;
import com.example.demo.error.InvalidYearException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
public class AuthController 
{
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping(path = "/login")
	public ResponseEntity<UserInfo> login(@RequestBody LoginRequest req, HttpServletResponse response)
	{
		String id = req.getId();
		String password = req.getPassword();
		Authentication authentication = new UsernamePasswordAuthenticationToken(id, password);
		try 
		{
			Authentication authenticationResponse = authenticationManager.authenticate(authentication);
			if(authenticationResponse != null && authenticationResponse.isAuthenticated() )
			{
				String secret = "sdfsdgadaefcde324refwdsvvldsnmipoeasdiosajpgsogesdfcv";
				SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
				String username = authenticationResponse.getName();
				String authorities = authenticationResponse.getAuthorities().stream().map(t -> t.getAuthority()).collect(Collectors.joining(","));
				String jwt = Jwts.builder().issuer("GCheck").subject("jwtToken")
							  .claim("username", username)
							  .claim("authorities", authorities)
							  .issuedAt(new Date()).expiration( new Date((new Date()).getTime() + 3000000))
							  .signWith(secretKey)
							  .compact();
			    ResponseCookie cookie = ResponseCookie.from("jwt_token", jwt)
			            .httpOnly(true)                 
			            .sameSite("Strict")            
			            .path("/")                      
			            .maxAge(Duration.ofMinutes(50)) 
			            .build();
			    Student user = studentRepository.findByUserID(id).orElse(null);
			    UserInfo userInfo = new UserInfo(user.getId(), user.getUserID(), user.getName(), user.getAdmissionYear(), user.getMajor());
		        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
				return ResponseEntity.status(HttpStatus.OK).body(userInfo);
			}
		} 
		catch (BadCredentialsException e) 
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletResponse response)
	{
	    ResponseCookie cookie = ResponseCookie.from("jwt_token", "")
	            .httpOnly(true)                 
	            .sameSite("Strict")            
	            .path("/")                      
	            .maxAge(Duration.ofMinutes(0)) 
	            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/register/checkDuplicate")
	public ResponseEntity<Map<String, String>> checkDuplicate(@RequestParam String id)
	{
		if(id.trim().isEmpty())
		{
		    throw new IdBlankException("공백이나 빈칸은 아이디가 될 수 없습니다.");
		}
		Optional<Student> student = studentRepository.findByUserID(id);
		if(!student.isEmpty())
		{
			throw new DuplicateIdException("이미 있는 아이디입니다.");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "사용 가능한 아이디입니다.");
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/register/departments")
	public ResponseEntity<List<Department>> findDepartmentsByName(@RequestParam String name)
	{
		List<Department> departments = departmentRepository.findByDepartmentContaining(name);
		if(departments.size() == 0 )
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departments);

		}
		return ResponseEntity.status(HttpStatus.OK).body(departments);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid Student student)
	{
		int admissionYear = student.getAdmissionYear();
		if( admissionYear > LocalDate.now().getYear() || admissionYear < 2020)
		{
			throw new InvalidYearException("지원하지 않는 년도입니다.");
		}
		Optional<Student> exist = studentRepository.findByUserID(student.getUserID());
		if(!exist.isEmpty())
		{
			throw new DuplicateIdException("이미 있는 아이디입니다.");
		}
		student.setRole("ROLE_USER");
		String password = student.getPassword();
		password = passwordEncoder.encode(password);
		System.out.println(password);
		student.setPassword(password);
		studentRepository.save(student);
		URI location = URI.create("/users/" + student.getId());
		return ResponseEntity.created(location).build();
	}
}
