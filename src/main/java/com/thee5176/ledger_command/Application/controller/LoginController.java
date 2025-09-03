package com.thee5176.ledger_command.Application.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
    private final AuthenticationManager authenticationManager;
	private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody Map<String, String> loginRequest) {

		String username = loginRequest.get("username");
		String password = loginRequest.get("password");

		Authentication authenticationRequest =
		UsernamePasswordAuthenticationToken.unauthenticated(username, password);

		try {
			Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

			// defensive checks
			if (authenticationResponse == null || !authenticationResponse.isAuthenticated()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			// set security context so the authenticated principal is available downstream
			SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
			return ResponseEntity.ok().build();

		} catch (AuthenticationException ex) {
			// failed authentication -> 401
			log.error("Authentication failed for user: " + username, ex);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
