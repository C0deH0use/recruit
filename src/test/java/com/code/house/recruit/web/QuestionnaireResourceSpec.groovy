package com.code.house.recruit.web

import com.code.house.recruit.common.nosql.documents.Questionnaire
import com.code.house.recruit.common.nosql.documents.User
import com.code.house.recruit.common.nosql.repos.QuestionRepo
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo
import com.code.house.recruit.common.nosql.repos.UserRepo
import com.code.house.recruit.common.testdata.TestQuestionData
import com.code.house.recruit.common.testdata.TestQuestionnaireData
import com.code.house.recruit.common.testdata.TestUserData
import com.code.house.recruit.web.reqres.NewCandidateQuestionPair
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Shared
import spock.lang.Specification

import static org.assertj.core.util.Lists.newArrayList
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class QuestionnaireResourceSpec extends Specification {

    private final ObjectMapper mapper = new ObjectMapper()

    @Autowired
    private MockMvc mvcMock

    @Autowired
    private QuestionnaireRepo questionnaireRepo

    @Autowired
    private QuestionRepo questionRepo

    @Autowired
    private UserRepo userRepo

    @Shared
    private String question1_id
    @Shared
    private String question1_Id

    def Questionnaire() {
        mapper.registerModule(new JodaModule())
    }

    void "should query for questionnaires"() {
        given:
        List<Questionnaire> questionnaires = newArrayList(TestQuestionnaireData.Questionnaire_1.entity(), TestQuestionnaireData.Questionnaire_2.entity())
        questionnaireRepo.saveAll(questionnaires)

        when:
        def response = mvcMock.perform(MockMvcRequestBuilders.get("/questionnaire")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath('$', hasSize(2)))
                .andDo(MockMvcResultHandlers.print())
    }

    void "should query for given questionnaire"() {
        given:
        question1_id = questionnaireRepo.findAll().get(0).getId()

        when:
        def performGetAction = mvcMock.perform(MockMvcRequestBuilders.get("/questionnaire/" + question1_id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        performGetAction
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.user.email', equalToIgnoringCase(TestQuestionnaireData.Questionnaire_1.USER.email)))
                .andExpect(jsonPath('$.user.firstName', equalToIgnoringCase(TestQuestionnaireData.Questionnaire_1.USER.firstName)))
                .andExpect(jsonPath('$.candidateQuestions', hasSize(TestQuestionnaireData.Questionnaire_1.candidateQuestions.size())))

    }

    void "should query for given user"() {
        given:
        def userId = TestUserData.USER_2.id

        when:
        def getAction = mvcMock.perform(MockMvcRequestBuilders.get("/questionnaire/by-user/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        getAction
                .andExpect(status().isOk())
                .andExpect(jsonPath('$', hasSize(1)))
                .andExpect(jsonPath('$[0].user.email', equalToIgnoringCase(TestQuestionnaireData.Questionnaire_2.USER.email)))
                .andExpect(jsonPath('$[0].candidateQuestions', hasSize(TestQuestionnaireData.Questionnaire_2.candidateQuestions.size())))
    }

    void "should only create questionnaires for existing user"() {
        given:
        def nonExistingUserEmail = TestUserData.USER_3.email

        when:
        def performPostAction = mvcMock.perform(MockMvcRequestBuilders.post("/questionnaire/" + nonExistingUserEmail)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        performPostAction
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))

    }

    void "should create new questionnaire for user"() {
        given:
        User user = userRepo.save(TestUserData.USER_2.build())

        when:
        def postAction = mvcMock.perform(MockMvcRequestBuilders.post("/questionnaire/" + user.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        postAction
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath('$.user.email', equalToIgnoringCase(TestUserData.USER_2.email)))
                .andExpect(jsonPath('$.user.firstName', equalToIgnoringCase(TestUserData.USER_2.firstName)))
                .andExpect(jsonPath('$.candidateQuestions', hasSize(0)))

    }

    void "should add new question to existing questionnaire" (){
        given:
        String question1_Id = questionRepo.save(TestQuestionData.QUESTION_1).id
        String candidateAnswer = "Answer to question"
        NewCandidateQuestionPair requestParam = NewCandidateQuestionPair
                .builder()
                .answer(candidateAnswer)
                .questionId(question1_Id)
                .build()

        when:
        def patchAction = mvcMock.perform(MockMvcRequestBuilders.patch("/questionnaire/" + question1_id + "/addQuestion")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestParam)))

        then:
        patchAction.andExpect(status().isNoContent())
    }

    void "should failed to add question to non existing questionnaire" (){
        given:
        String nonExistingQuestionnaireId = "nonExistingQuestionnaireId"
        String candidateAnswer = "Answer to question"
        NewCandidateQuestionPair requestParam = NewCandidateQuestionPair
                .builder()
                .answer(candidateAnswer)
                .questionId(question1_Id)
                .build()

        when:
        def patchAction = mvcMock.perform(MockMvcRequestBuilders.patch("/questionnaire/" + nonExistingQuestionnaireId + "/addQuestion")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestParam)))

        then:
        patchAction
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
    }
}
