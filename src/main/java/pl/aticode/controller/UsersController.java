package pl.aticode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.aticode.service.UserService;
import pl.aticode.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {

	private final UserService userService;
	
	
    public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            userService.saveUser(new UserStorage(null, userName, "password", LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fetchAllUsers")
    public ResponseEntity<List<UserStorage>> fetchAll() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }
}
