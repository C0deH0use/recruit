package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepo extends ReactiveMongoRepository<User,String> {
    Flux<User> findByEmail(String email);
}
