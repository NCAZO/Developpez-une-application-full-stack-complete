package com.openclassrooms.mddapi.configuration;

//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import com.openclassrooms.mddapi.services.JwtService;
//import com.openclassrooms.mddapi.services.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig{
//	
//	
//	@Autowired
//	private JwtService jwtService;
//	
//	@Autowired
//	private UserService userService;
//	
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		
//		http
//		.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
//		.addFilterAfter(new JwtAuthentificationFilter(jwtService, userService), BasicAuthenticationFilter.class)
//			.authorizeHttpRequests(authorize -> authorize
//					.requestMatchers("images/**").permitAll()
//					.requestMatchers("api/auth/register").permitAll()
//					.requestMatchers("api/auth/login").permitAll()
//					
//					.requestMatchers(							
//							// -- Swagger UI v2
//				            "/v2/api-docs",
//				            "/swagger-resources",
//				            "/swagger-resources/**",
//				            "/configuration/ui",
//				            "/configuration/security",
//				            "/swagger-ui.html",
//				            "/webjars/**",
//				            // -- Swagger UI v3 (OpenAPI)
//				            "/v3/api-docs/**",
//				            "/swagger-ui/**"
//			                )
//			        .permitAll()
//					
//					.anyRequest().authenticated()				
//					
//			)
////			SI PAS CA 403 lors du register
//			.csrf(AbstractHttpConfigurer::disable)
//			
//			.formLogin(formLogin -> formLogin.permitAll()
//			)
//			.rememberMe(org.springframework.security.config.Customizer.withDefaults());
//		
//		
//		return http.build();
//	}
//	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.addAllowedOrigin("http://localhost:4200"); // Add your allowed origins here
//		configuration.addAllowedMethod("*"); // Allow all HTTP methods
//		configuration.addAllowedHeader("*"); // Allow all headers
//		configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	 @Bean
//	  public ModelMapper modelMapper() {
//	      return new ModelMapper();
//	  }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.openclassrooms.mddapi.services.AuthEntryPointJwt;
import com.openclassrooms.mddapi.services.AuthTokenFilter;
import com.openclassrooms.mddapi.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
//(securedEnabled = true,
//jsr250Enabled = true,
//prePostEnabled = true) // by default
public class SpringSecurityConfig {
  
//  @Value("${spring.h2.console.path}")
//  private String h2ConsolePath;
  
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }
 
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
	.cors((cors) -> cors.configurationSource(corsConfigurationSource()))

    .csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth
          .requestMatchers(							
					// -- Swagger UI v2
		            "/v2/api-docs",
		            "/swagger-resources",
		            "/swagger-resources/**",
		            "/configuration/ui",
		            "/configuration/security",
		            "/swagger-ui.html",
		            "/webjars/**",
		            // -- Swagger UI v3 (OpenAPI)
		            "/v3/api-docs/**",
		            "/swagger-ui/**"
	                )
	        .permitAll()
          .requestMatchers("/api/auth/login").permitAll()
          .requestMatchers("/api/auth/register").permitAll()
              .anyRequest().authenticated()
        );
    
 // fix H2 database console: Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
    http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
    
    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }
  
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:4200"); // Add your allowed origins here
		configuration.addAllowedMethod("*"); // Allow all HTTP methods
		configuration.addAllowedHeader("*"); // Allow all headers
		configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
