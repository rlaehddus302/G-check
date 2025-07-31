package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGenrateFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null)
		{
			String secret = "sdfsdgadaefcde324refwdsvvldsnmipoeasdiosajpgsogesdfcv";
			SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
			String username = authentication.getName();
			String authorities = authentication.getAuthorities().stream().map(t -> t.getAuthority()).collect(Collectors.joining(","));
			String jwt = Jwts.builder().issuer("GCheck").subject("jwtToken")
						  .claim("username", username)
						  .claim("authorities", authorities)
						  .issuedAt(new Date()).expiration( new Date((new Date()).getTime() + 3000000))
						  .signWith(secretKey)
						  .compact();
			response.setHeader("Authorization", jwt);
		}
		filterChain.doFilter(request, response);
	}

}
