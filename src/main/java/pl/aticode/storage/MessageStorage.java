package pl.aticode.storage;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class MessageStorage {

	@Id
	private String id;
	private String username;
	private String messageContent;
	private LocalDateTime dateTimeSave;
}
