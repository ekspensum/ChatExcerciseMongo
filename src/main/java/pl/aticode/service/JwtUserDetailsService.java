package pl.aticode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.aticode.dal.postgres.EmployeeRepository;
import pl.aticode.entity.Employee;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> roles = new ArrayList<>();
		Employee employee = employeeRepository.findByUserUsername(username);
		for (int i = 0; i < employee.getUser().getRoles().size(); i++) {
			roles.add(new SimpleGrantedAuthority(employee.getUser().getRoles().get(i).getRole()));
		}
		return new User(employee.getUser().getUsername(), employee.getUser().getPassword(), roles);
	}

}
