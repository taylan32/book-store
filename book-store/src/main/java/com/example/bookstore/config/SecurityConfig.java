package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.bookstore.security.JwtAccessDeniedHandler;
import com.example.bookstore.security.JwtAuthenticationEntryPoint;
import com.example.bookstore.security.JwtFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true) // role göre erişim olacaksa kullanmak gerekir
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

	private final JwtFilter jwtFilter;
	private final JwtAccessDeniedHandler accessDeniedHandler;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	public SecurityConfig(JwtFilter jwtFilter, JwtAccessDeniedHandler accessDeniedHandler, JwtAuthenticationEntryPoint authenticationEntryPoint) {
		this.jwtFilter = jwtFilter;
		this.accessDeniedHandler = accessDeniedHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.cors().and()
				.authorizeRequests((auth) -> {
					auth.antMatchers("/api/admin").hasAuthority("ADMIN");
					auth.antMatchers("/api/users").hasAnyAuthority("ADMIN", "USER");
					auth.anyRequest().authenticated();
				})
				.formLogin().disable()
				.httpBasic().disable()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	// resimler gibi public tutmak istediklerimiz için
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web -> web.ignoring().antMatchers("/api/images/**", "/api/auth/**", "/api/public"));
	}
	
	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}
	
	
	
}
