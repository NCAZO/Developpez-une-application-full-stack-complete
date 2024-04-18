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