package pl.aticode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.aticode.dal.MessageRepository;
import pl.aticode.storage.MessageStorage;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public void saveMessage(MessageStorage messageStorage) throws Exception {
		messageRepository.insert(messageStorage);
	}
}
