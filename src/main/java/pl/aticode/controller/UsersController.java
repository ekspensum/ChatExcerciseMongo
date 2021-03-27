package pl.aticode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.aticode.service.UserService;
import pl.aticode.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@CrossOrigin
public class UsersController {

	@Autowired
	private UserService userService;
	
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

//    @GetMapping("/fetchAllUsers")
//    public Set<String> fetchAll() {
//        return UserStorage.getInstance().getUsers();
//    }
}
