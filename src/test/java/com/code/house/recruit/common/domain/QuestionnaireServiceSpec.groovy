package com.code.house.recruit.common.domain

import com.code.house.recruit.RecruitApplication
import com.code.house.recruit.common.domain.exceptions.ObjectNotFoundException
import com.code.house.recruit.common.nosql.documents.Question
import com.code.house.recruit.common.nosql.documents.Questionnaire
import com.code.house.recruit.common.nosql.documents.User
import com.code.house.recruit.common.nosql.documents.enums.QuestionCategory
import com.code.house.recruit.common.nosql.documents.enums.QuestionDifficulty
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo
import com.code.house.recruit.common.nosql.repos.UserRepo
import org.joda.time.DateTime
import org.joda.time.Minutes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = RecruitApplication.class)
class QuestionnaireServiceSpec extends Specification {

    private static final String CANDIDATE_EMAIL = "canA@new.pl"
    private static final String NON_EXISTING_CANDIDATE_EMAIL = "non-existing@user.pl"

    @Autowired
    private UserRepo userRepo

    @Autowired
    private QuestionnaireRepo repo

    @Autowired
    private QuestionnaireService sut

    private static User newUser = User.builder()
            .firstName("Candidate")
            .lastName("A")
            .status(User.Status.ACTIVE)
            .email(CANDIDATE_EMAIL)
            .build()

    void "should create new questionnaire for candidate"() {
        given:
        userRepo.save(newUser)
        String userId = userRepo.findByEmail(CANDIDATE_EMAIL).id
        assert userId

        when:
        sut.createQuestionnaireForUser(CANDIDATE_EMAIL)
        List<Questionnaire> questionnaires = repo.findByUserEmail(CANDIDATE_EMAIL)

        then:
        questionnaires.size() == 1
        Minutes.minutesBetween(questionnaires.get(0).createdDate, DateTime.now()).getMinutes() == 0
    }

    void "should add new questionnarie for given candidate"() {
        given:
        assert repo.findByUserEmail(CANDIDATE_EMAIL).size() == 1

        when:
        sut.createQuestionnaireForUser(CANDIDATE_EMAIL)
        def questionnaires = repo.findByUserEmail(CANDIDATE_EMAIL)

        then:
        questionnaires.size() == 2
        Minutes.minutesBetween(questionnaires.get(1).createdDate, DateTime.now()).getMinutes() == 0
    }

    void "should throw exception when creating questionnaire for non-existing candidates"() {
        when:
        sut.createQuestionnaireForUser(NON_EXISTING_CANDIDATE_EMAIL)

        then:
        thrown ObjectNotFoundException
    }

    void "should add question without any candidate answer"() {
        given:
        String givenQuestionnaireId = repo.findByUserEmail(CANDIDATE_EMAIL).get(0).id
        Question givenQuestion = Question.builder()
                .category(QuestionCategory.IT_BASICS)
                .difficulty(QuestionDifficulty.Junior)
                .question("Question_String")
                .answer("Answer for question")
                .hint("Question hints")
                .build()

        when:
        sut.addQuestions(givenQuestionnaireId, givenQuestion, "")
        def questionnaire = repo.findById(givenQuestionnaireId)

        then:
        questionnaire.isPresent()
        questionnaire.get().candidateQuestions.size() == 1
    }

    void "should throw exception if no questionnaire found"() {
        String NON_EXISTING_QUESTIONNAIRE_ID = "Non-existing_id"
        when:
        sut.addQuestions(NON_EXISTING_QUESTIONNAIRE_ID, null, null)

        then:
        thrown ObjectNotFoundException
    }

    void "should throw exception if question is null"() {
        given:
        String givenQuestionnaireId = repo.findByUserEmail(CANDIDATE_EMAIL).get(0).id

        when:
        sut.addQuestions(givenQuestionnaireId, null, null)

        then:
        thrown NullPointerException
    }

}
