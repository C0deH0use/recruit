package com.code.house.recruit.data.nosql.repos;

import com.code.house.recruit.data.nosql.documents.Questionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepo extends MongoRepository<Questionnaire, String> {

    List<Questionnaire> findByUserId(@Param("id") String userId);
}
