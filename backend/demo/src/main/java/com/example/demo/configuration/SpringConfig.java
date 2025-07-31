package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.filter.CustomCsrfFilter;
import com.example.demo.filter.JWTTokenGenrateFilter;
import com.example.demo.filter.JWTTokenValidationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

@Configuration
public class SpringConfig {
	
	@Bean
	SecurityFilterChain cutomeSecurityFilterChain(HttpSecurity http) throws Exception 
	{
		http.authorizeHttpRequests((requests) -> requests.
				requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated());
		http.headers(headers -> headers.frameOptions().disable());
		http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.cors(withDefaults());
		http.csrf(t -> t.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
						.ignoringRequestMatchers("/h2-console/**", "/loginIn"));
		http.addFilterAfter(new CustomCsrfFilter(), CsrfFilter.class);
		http.addFilterAfter(new JWTTokenGenrateFilter(), BasicAuthenticationFilter.class);
		http.addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class);
		http.formLogin(t -> t.disable());
		http.httpBasic(withDefaults());
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*")); // 모든 origin 허용
        config.setAllowedMethods(List.of("*"));        // 모든 HTTP method 허용
        config.setAllowedHeaders(List.of("*"));        // 모든 헤더 허용
        config.setAllowCredentials(true);              // 쿠키/인증 허용
        config.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
}
