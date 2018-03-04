package com.code.house.recruit.common.domain;

import com.code.house.recruit.common.nosql.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionnaireService {

    private final UserRepo userRepo;


    @Autowired
    public QuestionnaireService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public void createQuestionnaireForUser(String userId) {

    }
}
