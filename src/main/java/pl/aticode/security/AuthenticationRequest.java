package pl.aticode.security;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationRequest {

	private String username;
	private String password;
	private String socialUserId;
	private String socialUserToken;
	private String provider;
	
	public AuthenticationRequest() {
	}
	
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequest(String socialUserId, String socialUserToken, String provider) {
		this.socialUserId = socialUserId;
		this.socialUserToken = socialUserToken;
		this.provider = provider;
	}
	
}
