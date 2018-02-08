package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
}
