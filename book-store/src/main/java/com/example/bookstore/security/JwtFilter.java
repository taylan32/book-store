package com.example.bookstore.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.bookstore.service.user.UserDetailsServiceImpl;
import com.example.bookstore.utils.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final TokenGenerator tokenGenerator;
	private final UserDetailsServiceImpl userDetailsService;

	public JwtFilter(TokenGenerator tokenGenerator, UserDetailsServiceImpl userDetailsService) {
		this.tokenGenerator = tokenGenerator;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		String userName;
		try {
			if (token != null) {
				userName = tokenGenerator.verifyJwt(token).getSubject();
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			Map<String, String> errors = new HashMap<>();
			errors.put("error", exception.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(mapper.writeValueAsString(errors));
		}

	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith("Bearer ")) {
			return null;
		}
		return header.substring(7);
	}

}
