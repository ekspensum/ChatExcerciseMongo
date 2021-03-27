package pl.aticode.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pl.aticode.storage.UserStorage;

public interface UserRepository extends MongoRepository<UserStorage, String> {
	
	@Query(value = "{username : ?0}", exists = true)
	Boolean isBooksAvailableByWriter(String username); 

}
