package pl.aticode.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final JwtUserDetailsService jwtUserDetailsService;

	
	public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService,
			JwtUserDetailsService jwtUserDetailsService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.jwtUserDetailsService = jwtUserDetailsService;
	}


	@PostMapping(path = "/authenticate")
	public ResponseEntity<Void> createAuthenticationTokenForRegisteredUser(@RequestBody AuthenticationRequest authenticationRequest)
			 throws AuthenticationException {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtService.generateToken(userDetails);
		
		return ResponseEntity.ok().header("Authorization", "Bearer "+token).build();
	}
	

}
