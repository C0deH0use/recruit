package com.code.house.recruit.data.nosql.repos;

import com.code.house.recruit.data.nosql.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/users", collectionResourceRel = "users")
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(@Param("email") String email);
}
