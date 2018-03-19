package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(path = "/users", collectionResourceRel = "users")
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
}
