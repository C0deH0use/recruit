package com.code.house.recruit.data.nosql.repos;


import com.code.house.recruit.data.nosql.documents.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends MongoRepository<Question, String> {
}
