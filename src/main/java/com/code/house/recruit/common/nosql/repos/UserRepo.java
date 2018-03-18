package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
}
