package com.code.house.recruit.web;


import com.code.house.recruit.common.domain.QuestionnaireService;
import com.code.house.recruit.common.domain.exceptions.ObjectNotFoundException;
import com.code.house.recruit.common.nosql.documents.Questionnaire;
import com.code.house.recruit.common.nosql.repos.QuestionnaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/questionnaire", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireResource {

    @Autowired
    private QuestionnaireService service;

    @Autowired
    private QuestionnaireRepo repository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Questionnaire>> getQuestionnaires(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/{questionnaireId}")
    public ResponseEntity<Questionnaire> selectById(@PathVariable("questionnaireId") @NotBlank String questionnaireId){
        Questionnaire questionnaire = repository.findById(questionnaireId)
                .orElseThrow(() -> new ObjectNotFoundException("Could not find by given Id"));


        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

}
