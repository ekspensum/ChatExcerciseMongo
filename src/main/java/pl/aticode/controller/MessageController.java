package pl.aticode.controller;

import pl.aticode.model.MessageModel;
import pl.aticode.service.MessageService;
import pl.aticode.service.UserService;
import pl.aticode.storage.MessageStorage;
import pl.aticode.storage.UserStorage;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @MessageMapping("/chat/{username}")
    public void sendMessage(@DestinationVariable String username, MessageModel message) {
    	
    	boolean isExists = userService.findUser(username);
//        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            try {
            	simpMessagingTemplate.convertAndSend("/topic/messages/" + username, message);
				messageService.saveMessage(new MessageStorage(null, username, message.getMessage(), LocalDateTime.now()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("handling send message: " + message + " to: " + username);
        }
    }
}
