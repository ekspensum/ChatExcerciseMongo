package pl.aticode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import pl.aticode.service.JwtUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthenticationEntryPointImpl authenticationEntryPointImpl;
	private final JwtUserDetailsService jwtUserDetailsService;

	public ServerSecurityConfig(AuthenticationEntryPointImpl authenticationEntryPointImpl,
			JwtUserDetailsService jwtUserDetailsService) {
		super();
		this.authenticationEntryPointImpl = authenticationEntryPointImpl;
		this.jwtUserDetailsService = jwtUserDetailsService;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(jwtUserDetailsService);
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/authenticateRegisterdUser", "/api/authenticateSocialUser", "/api/verifyReCaptcha", 
						"/api/sendEmailByContactUs", "/api/forgetPassword", "/api/resetPasswordByLink", "/api/activationPatient", 
						"/api/addPatientByRegistrationPage", "/api/addSocialUser", "/api/getCompanyData", "/api/getAllDoctorsForAgendaPage/*/*")
				.permitAll().anyRequest()
				.authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

}
