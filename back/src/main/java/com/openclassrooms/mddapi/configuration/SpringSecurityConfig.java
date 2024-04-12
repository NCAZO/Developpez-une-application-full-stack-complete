package com.openclassrooms.mddapi.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.openclassrooms.mddapi.services.JwtService;
import com.openclassrooms.mddapi.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
@Slf4j
public class SpringSecurityConfig {

//	@Autowired
//	private JwtService jwtService;
//
//	@Autowired
//	private UserService userService;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			     .csrf(csrf -> csrf.disable())
			     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			     .authorizeHttpRequests(auth -> auth
			      .requestMatchers("/api/auth/login**", "/api/auth/register").permitAll()
			      .anyRequest().authenticated())
			     .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
			     .httpBasic(Customizer.withDefaults())
			     .build();
	}
	
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.addFilterAfter(new JwtAuthentificationFilter(jwtService, userService), BasicAuthenticationFilter.class)
//				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers("images/**").permitAll()
//						.requestMatchers("api/auth/register").permitAll()
//						.requestMatchers("api/auth/login").permitAll()
//						.requestMatchers(
//								// -- Swagger UI v2
//								"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
//								"/configuration/security", "/swagger-ui.html", "/webjars/**",
//								// -- Swagger UI v3 (OpenAPI)
//								"/v3/api-docs/**", "/swagger-ui/**")
//						.permitAll()
//						.anyRequest().authenticated()
//
//				)
//				//			SI PAS CA 403 lors du register
//				.csrf(AbstractHttpConfigurer::disable)
//
//				.formLogin(formLogin -> formLogin.permitAll())
//				.rememberMe(org.springframework.security.config.Customizer.withDefaults());
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
//	@Value("${jwtKey}")
//    private String jwtKey;
//
//    private final UserService userDetailsService;
//
//    public SpringSecurityConfig(UserService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    /**
//     * This method is used to create an AuthenticationManager bean.
//     * @return an AuthenticationManager bean
//     */
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        var authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        log.info("AuthenticationManager bean is successfully created.");
//        return new ProviderManager(authProvider);
//    }
//
//    /**
//     * This method is used to create a SecurityFilterChain bean.
//     * @param http the HttpSecurity object
//     * @return a SecurityFilterChain bean
//     * @throws Exception if an error occurs
//     */
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////		JwtAuthentificationFilter authenticationFilter = new JwtAuthentificationFilter(jwtService, userDetailsService);
//
//		return http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/auth/register", "/api/auth/login",
//						// -- Swagger UI v2
//						"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
//						"/configuration/security", "/swagger-ui.html", "/webjars/**",
//						// -- Swagger UI v3 (OpenAPI)
//						"/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui.html", "/swagger-ui/**", "/images/**",
//						"static/images/**").permitAll().anyRequest().authenticated())
//				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//				//.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
//				.build();
//	}

//    @Bean
//	JwtDecoder jwtDecoder() {
//		SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length, "RSA");
//		return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
//	}
//
//	@Bean
//	JwtEncoder jwtEncoder() {
//		return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
//	}
   

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("PasswordEncoder bean is successfully created.");
        return new BCryptPasswordEncoder();
    }
}