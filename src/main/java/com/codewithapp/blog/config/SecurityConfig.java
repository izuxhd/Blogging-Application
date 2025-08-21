package com.codewithapp.blog.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codewithapp.blog.security.CutomUserDetailService;
import com.codewithapp.blog.security.JwtAuthencticationFilter;
import com.codewithapp.blog.security.JwtAuthenticationEntryPoint;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
@EnableMethodSecurity

public class SecurityConfig   {
	@Autowired
	private  final  CutomUserDetailService  cutomUserDetailsService;
	  @Autowired
	    private   JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	  @Autowired
	    
	      private  JwtAuthencticationFilter jwtAuthencticationFilter;
	  
	  
	  
	  
	  public SecurityConfig(CutomUserDetailService customUserDetailsService) {
	        this.cutomUserDetailsService = customUserDetailsService;
	    }
	  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // 🔒 Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/**").authenticated())
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
            http.addFilterBefore(jwtAuthencticationFilter, UsernamePasswordAuthenticationFilter.class);
            http.authenticationProvider(authenticationProvider());

        return http.build();
    }
    
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.customUserDetailsService)
//            .passwordEncoder(passwordEncoder());
//    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(cutomUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

   
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationManager) throws Exception {
        return authenticationManager.getAuthenticationManager();
    }
    
  
    @Bean
    public  PasswordEncoder  passwordEncoder() {
    	return  new   BCryptPasswordEncoder();
    }

}
