package com.openclassrooms.mddapi.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassrooms.mddapi.services.JwtService;
import com.openclassrooms.mddapi.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;;

@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

//	public JwtAuthentificationFilter(JwtService jwtService, UserService userService) {
//		this.jwtService = jwtService;
//		this.userService = userService;
//	}

	private JwtService jwtService;

	private UserService userService;

	private UserDetails userDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("ddd");
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		String userEmail = null;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);

		// Vérifier si le token est valide
		if (jwtService.isTokenValid(jwt, userDetails)) {
			// Récupérer l'adresse e-mail de l'utilisateur
			userEmail = jwtService.extractUserName(jwt);
		} else {
			// Le token est invalide
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userService.loadUserByUsername(userEmail);

			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
