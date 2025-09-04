package com.thee5176.ledger_command.config;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.thee5176.ledger_command.domain.model.credential.Tables;
import com.thee5176.ledger_command.security.JwtAuthenticationFilter;
import com.thee5176.ledger_command.security.JwtService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final DSLContext dslContext;

    @Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			// Load user details from the database using jOOQ
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				final UserDetails user = 
					dslContext.selectFrom(Tables.USER)
					.where(Tables.USER.USERNAME.eq(username))
					.fetchOneInto(UserDetails.class);

				if (user == null) {
					throw new UsernameNotFoundException("User not found with username: " + username);
				}
				
				return User.builder()
						.username(user.getUsername())
						.password(user.getPassword())
						.roles("USER")
						.build();
			}
		};
	}

	@Bean DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		return new JwtAuthenticationFilter(jwtService, userDetailsService);
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
		// Allow request path unauthenticated access and disable CSRF for simplicity
		http
			.csrf((csrf -> csrf.disable()))
			.authorizeHttpRequests(auth -> 
				auth.requestMatchers("/api/v1/auth/**").permitAll()
				.anyRequest().authenticated()
				)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions; use JWTs
			)
			.authenticationProvider(daoAuthenticationProvider())
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}