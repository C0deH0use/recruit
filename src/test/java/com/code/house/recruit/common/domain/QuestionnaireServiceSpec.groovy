package com.code.house.recruit.common.domain

import com.code.house.recruit.RecruitApplication
import com.code.house.recruit.common.domain.exceptions.ObjectNotFoundException
import com.code.house.recruit.common.nosql.documents.Question
import com.code.house.recruit.common.nosql.documents.Questionnaire
import com.code.house.recruit.common.nosql.documents.User
import com.code.house.recruit.common.nosql.documents.enums.QuestionCategory
import com.code.house.recruit.common.nosql.documents.enums.QuestionDifficulty
import com.code.house.recruit.common.nosql.repos.QuestionRepo
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo
import com.code.house.recruit.common.nosql.repos.UserRepo
import org.joda.time.DateTime
import org.joda.time.Minutes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = RecruitApplication.class)
class QuestionnaireServiceSpec extends Specification {


    private static final String CANDIDATE_ID = "newuser1"
    private static final String CANDIDATE_EMAIL = "canA@new.pl"
    private static final String NON_EXISTING_CANDIDATE_EMAIL = "non-existing@user.pl"

    @Autowired
    private UserRepo userRepo

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private QuestionnaireRepo repo

    @Autowired
    private QuestionnaireService sut

    private User newUser = User.builder()
            .id(CANDIDATE_ID)
            .firstName("Candidate")
            .lastName("A")
            .status(User.Status.ACTIVE)
            .email(CANDIDATE_EMAIL)
            .build()

    void "should create new questionnaire for candidate"() {
        given:
        String newUserId = userRepo.save(newUser).id
        assert newUserId == CANDIDATE_ID

        when:
        sut.createQuestionnaireForUser(newUserId)
        List<Questionnaire> questionnaires = repo.findByUserId(newUserId)

        then:
        questionnaires.size() == 1
        Minutes.minutesBetween(questionnaires.get(0).createdDate, DateTime.now()).getMinutes() == 0
    }

    void "should add new questionnarie for given candidate"() {
        given:
        assert repo.findByUserId(CANDIDATE_ID).size() == 1

        when:
        sut.createQuestionnaireForUser(CANDIDATE_ID)
        List<Questionnaire> questionnaires = repo.findByUserId(CANDIDATE_ID)

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
        String givenQuestionnaireId = repo.findByUserId(CANDIDATE_ID).get(0).id
        Question question = Question.builder()
                .category(QuestionCategory.IT_BASICS)
                .difficulty(QuestionDifficulty.Junior)
                .question("Question_String")
                .answer("Answer for question")
                .hint("Question hints")
                .build()
        String givenQuestionId = questionRepo.save(question).id

        when:
        sut.addQuestions(givenQuestionnaireId, givenQuestionId, "")
        Optional<Questionnaire> questionnaire = repo.findById(givenQuestionnaireId)

        then:
        questionnaire.isPresent()
        questionnaire.get().candidateQuestions.size() == 1
    }

    void "should throw exception if no questionnaire found"() {
        given:
        String NON_EXISTING_QUESTION_ID = "Non-existing_id"
        String NON_EXISTING_QUESTIONNAIRE_ID = "Non-existing_id"

        when:
        sut.addQuestions(NON_EXISTING_QUESTIONNAIRE_ID, NON_EXISTING_QUESTION_ID, "")

        then:
        thrown ObjectNotFoundException
    }

    void "should throw exception if question is null"() {
        given:
        String givenQuestionnaireId = repo.findByUserId(CANDIDATE_ID).get(0).id

        when:
        sut.addQuestions(givenQuestionnaireId, null, "")

        then:
        thrown NullPointerException
    }

}
