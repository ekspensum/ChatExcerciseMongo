package pl.aticode.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pl.aticode.dal.mongo.MessageRepository;
import pl.aticode.dal.mongo.UserStorageRepository;
import pl.aticode.dal.postgres.EmployeeRepository;
import pl.aticode.entity.Employee;
import pl.aticode.storage.MessageStorage;
import pl.aticode.storage.UserStorage;

@Service
public class UserService {

	private final UserStorageRepository userRepository;
	private final MessageRepository messageRepository;
	private final EmployeeRepository employeeRepository;
	

	public UserService(UserStorageRepository userRepository, MessageRepository messageRepository, EmployeeRepository employeeRepository) {
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
		this.employeeRepository = employeeRepository;
	}

	@PostConstruct
	public void addUser() throws Exception {
		userRepository.save(new UserStorage("1", "customer1@gmail.com", "password", LocalDateTime.now()));
		userRepository.save(new UserStorage("2", "customer2@gmail.com", "password", LocalDateTime.now()));
		List<UserStorage> findAllUsers = userRepository.findAll();
		for (UserStorage userStorage : findAllUsers) {
			System.out.println(userStorage.toString());
		}

//		messageRepository.insert(new MessageStorage(null, "username1", "message content 1", LocalDateTime.now()));
//		messageRepository.insert(new MessageStorage(null, "username2", "message content 2", LocalDateTime.now()));
//		messageRepository.deleteAll();

		List<MessageStorage> findAllMessages = messageRepository.findAll();
		for (MessageStorage messageStorage : findAllMessages) {
			System.out.println(messageStorage.toString());
		}
		
		Iterable<Employee> findAllEmployee = employeeRepository.findAll();
	    List<Employee> employeeList = new ArrayList<>();
	    findAllEmployee.forEach(employeeList::add);
	    for (Employee employee : employeeList) {
			System.out.println("EMPLOYEE: "+employee.getUser().getFirstName()+" "+employee.getUser().getLastName()+" "+employee.getUser().getUsername());
		}
	    
	    Employee findByUserUsername = employeeRepository.findByUserUsername("admin1");
	    System.out.println(findByUserUsername.getUser().getLastName());
	}

//	public boolean findUser(String username) {
//		return userRepository.exists(new Example<UserStorage>() {
//
//			@Override
//			public UserStorage getProbe() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public ExampleMatcher getMatcher() {
//				ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//				
//				return exampleMatcher
//						.withIgnoreCase()
//						.withIgnorePaths("id")
//						.withMatcher("username", ign);
//			}
//
//		});
//	}
	
	public boolean findUser(String username) {
		return userRepository.isUserExist(username);
	}
	
	public void saveUser(UserStorage userStorage) throws Exception {
		userRepository.insert(userStorage);
	}
	
	public List<UserStorage> fetchAllUsers() {
		return userRepository.findAll();
	}
}
