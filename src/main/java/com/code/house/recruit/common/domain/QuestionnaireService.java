package com.code.house.recruit.common.domain;

import com.code.house.recruit.common.domain.exceptions.ObjectNotFoundException;
import com.code.house.recruit.common.nosql.documents.Question;
import com.code.house.recruit.common.nosql.documents.Questionnaire;
import com.code.house.recruit.common.nosql.documents.User;
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo;
import com.code.house.recruit.common.nosql.repos.UserRepo;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
public class QuestionnaireService {

    private final UserRepo userRepo;

    private final QuestionnaireRepo repo;

    @Autowired
    public QuestionnaireService(UserRepo userRepo, QuestionnaireRepo questionnaireRepo) {
        this.userRepo = userRepo;
        this.repo = questionnaireRepo;
    }

    @Transactional
    public Questionnaire createQuestionnaireForUser(String userEmail) {
        final User candidate = Optional.ofNullable(userRepo.findByEmail(userEmail))
                .orElseThrow(() -> new ObjectNotFoundException("Could No find User with email: " + userEmail));
        Questionnaire newQuestionnaireForCandidate = Questionnaire.builder()
                .user(candidate)
                .build();
        return repo.save(newQuestionnaireForCandidate);
    }

    @Transactional
    public void addQuestions(String questionnaireId, Question question, String candidateAnswer) {
        Questionnaire questionnaire = repo.findById(questionnaireId)
                .orElseThrow(() -> new ObjectNotFoundException("Could not find questionnaire with id: " + questionnaireId));

        Objects.requireNonNull(question, "Question is required");
        Objects.requireNonNull(candidateAnswer, "Answer is required");

        ImmutablePair<Question, String> pair = new ImmutablePair<>(question, candidateAnswer);
        questionnaire.getCandidateQuestions().add(pair);

        repo.save(questionnaire);
    }
}
