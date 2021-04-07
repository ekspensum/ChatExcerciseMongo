package pl.aticode.dal.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pl.aticode.storage.UserStorage;

public interface UserStorageRepository extends MongoRepository<UserStorage, String> {
	
	@Query(value = "{username : ?0}", exists = true)
	Boolean isUserExist(String username); 

}
