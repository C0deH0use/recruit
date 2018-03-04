package com.code.house.recruit.common.domain

import com.code.house.recruit.common.nosql.documents.User
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo
import com.code.house.recruit.common.nosql.repos.UserRepo
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@SpringBootTest
@RunWith(SpringRunner.class)
class QuestionnaireServiceSpec extends Specification {

    @Autowired
    private UserRepo userRepo

    @Autowired
    private QuestionnaireRepo repo

    @Autowired
    private QuestionnaireService sut
    private static User newUser = new User.UserBuilder()
            .firstName("Candidate")
            .lastName("A")
            .status(User.Status.ACTIVE)
            .email("canA@new.pl")
            .build()

    void "should create new questionnaire for candidate A"() {
        given:
        userRepo.save(newUser)
        String userId = userRepo.findByEmail(newUser.email).id
        assert userId

        when:
        sut.createQuestionnaireForUser(userId)

        then:
        repo.count() > 0
    }
}
