package com.code.house.recruit.web

import com.code.house.recruit.common.nosql.documents.Questionnaire
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo
import com.code.house.recruit.common.testdata.TestQuestionnaireData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import static org.assertj.core.util.Lists.newArrayList
import static org.hamcrest.collection.IsCollectionWithSize.hasSize

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class QuestionnaireResourceSpec extends Specification {

    @Autowired
    private MockMvc mvcMock

    @Autowired
    private QuestionnaireRepo questionnaireRepo

    void "should query for questionnaires"() {
        given:
        List<Questionnaire> questionnaires = newArrayList(TestQuestionnaireData.Questionnaire_1, TestQuestionnaireData.Questionnaire_2)
        questionnaireRepo.saveAll(questionnaires)

        when:
        def getAction = mvcMock.perform(MockMvcRequestBuilders.get("/questionnaire")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        getAction
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("body", hasSize(2)))
    }

}
