package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.Questionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepo extends MongoRepository<Questionnaire, String> {

    List<Questionnaire> findByUserEmail(String email);
}
