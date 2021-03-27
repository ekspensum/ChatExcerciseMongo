package pl.aticode.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import pl.aticode.dal.MessageRepository;
import pl.aticode.dal.UserRepository;
import pl.aticode.storage.MessageStorage;
import pl.aticode.storage.UserStorage;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;

	@PostConstruct
	public void addUser() throws Exception {
		userRepository.save(new UserStorage("1", "employee1@gmail.com", "password", LocalDateTime.now()));
		userRepository.save(new UserStorage("2", "employee2@gmail.com", "password", LocalDateTime.now()));
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
		return userRepository.isBooksAvailableByWriter(username);
	}
	
	public void saveUser(UserStorage userStorage) throws Exception {
		userRepository.insert(userStorage);
	}
}
