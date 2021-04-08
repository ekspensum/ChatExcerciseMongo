package pl.aticode.controller;

import pl.aticode.model.MessageModel;
import pl.aticode.service.MessageService;
import pl.aticode.service.UserService;
import pl.aticode.storage.MessageStorage;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final UserService userService;
    

    public MessageController(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService,
			UserService userService) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.messageService = messageService;
		this.userService = userService;
	}


	@MessageMapping("/chat/{username}")
    public void sendMessage(@DestinationVariable String username, MessageModel message) {
    	
    	boolean isExists = userService.findUser(username);
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
