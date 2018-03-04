package com.code.house.recruit.common.nosql.repos;

import com.code.house.recruit.common.nosql.documents.CandidateQuestionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends MongoRepository <CandidateQuestionnaire, String> {
}
