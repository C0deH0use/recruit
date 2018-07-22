package com.code.house.recruit.domain.service;

import com.code.house.recruit.data.nosql.documents.Question;
import com.code.house.recruit.data.nosql.documents.Questionnaire;
import com.code.house.recruit.data.nosql.documents.User;
import com.code.house.recruit.data.nosql.repos.QuestionRepo;
import com.code.house.recruit.data.nosql.repos.QuestionnaireRepo;
import com.code.house.recruit.data.nosql.repos.UserRepo;
import com.code.house.recruit.domain.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
public class QuestionnaireService {

    private final UserRepo userRepo;

    private final QuestionnaireRepo repo;

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionnaireService(UserRepo userRepo, QuestionnaireRepo questionnaireRepo, QuestionRepo questionRepo) {
        this.userRepo = userRepo;
        this.repo = questionnaireRepo;
        this.questionRepo = questionRepo;
    }

    @Transactional
    public void addQuestions(String questionnaireId, String questionId, String candidateAnswer) {
        Objects.requireNonNull(questionnaireId, "Questionnaire is required");
        Objects.requireNonNull(questionId, "Question is required");

        Questionnaire questionnaire = repo.findById(questionnaireId)
                .orElseThrow(() -> new ObjectNotFoundException("Could not find questionnaire with id: " + questionnaireId));
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new ObjectNotFoundException("Could not find question with id: " + questionId));


        ImmutablePair<Question, String> pair = new ImmutablePair<>(question, candidateAnswer);
        questionnaire.getCandidateQuestions().add(pair);

        repo.save(questionnaire);
    }

    @Transactional
    public Questionnaire createQuestionnaireForUser(String userId) {
        final User candidate = userRepo.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Could no find User with id: " + userId));
        log.info("Create new questionnaire for user {}", candidate.getEmail());
        Questionnaire newQuestionnaireForCandidate = Questionnaire.builder()
                .user(candidate)
                .build();
        return repo.save(newQuestionnaireForCandidate);
    }
}
