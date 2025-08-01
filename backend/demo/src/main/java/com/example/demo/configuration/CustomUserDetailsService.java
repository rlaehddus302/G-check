package com.example.demo.configuration;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService 
{
	@Autowired
	private StudentRepository studentRepository;  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<Student> student = studentRepository.findByUserID(username);
		Student user = student.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new User(username, user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
	}
	
}
