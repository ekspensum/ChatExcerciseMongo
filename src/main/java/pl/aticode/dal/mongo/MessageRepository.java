package pl.aticode.dal.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.aticode.storage.MessageStorage;

public interface MessageRepository extends MongoRepository<MessageStorage, String> {

}
