package com.code.house.recruit.web.rest;

import com.code.house.recruit.data.nosql.documents.Questionnaire;
import com.code.house.recruit.data.nosql.repos.QuestionnaireRepo;
import com.code.house.recruit.domain.exceptions.ObjectNotFoundException;
import com.code.house.recruit.domain.service.QuestionnaireService;
import com.code.house.recruit.web.rest.reqres.NewCandidateQuestionPair;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/questionnaire",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireResource {

    @Autowired
    private QuestionnaireService service;

    @Autowired
    private QuestionnaireRepo repository;

    @ResponseBody
    @PatchMapping("/{questionnaireId}/addQuestion")
    public ResponseEntity addQuestion(
            @PathVariable("questionnaireId") @NotBlank String questionnaireId,
            @RequestBody @Valid NewCandidateQuestionPair questionPair) {
        service.addQuestions(questionnaireId, questionPair.getQuestionId(), questionPair.getAnswer());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @PostMapping("/{uuid}")
    public ResponseEntity<Questionnaire> createNewForUser(
            @PathVariable("uuid") @NotBlank String userId) {
        Questionnaire newQuestionnaire = service.createQuestionnaireForUser(userId);

        return new ResponseEntity<>(newQuestionnaire, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Questionnaire>> getQuestionnaires() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/by-user/{uuid}")
    public ResponseEntity<List<Questionnaire>> getQuestionnairesByUser(
            @PathVariable("uuid") @NotBlank String uuid) {
        List<Questionnaire> userQuestionnaires = repository.findByUserId(uuid);
        return new ResponseEntity<>(userQuestionnaires, HttpStatus.OK);
    }

    /**
     * Get given questionnaire
     *
     * @param questionnaireId id
     * @return
     */
    @ResponseBody
    @GetMapping("/{questionnaireId}")
    public ResponseEntity<Questionnaire> selectById(
            @PathVariable("questionnaireId") @NotBlank String questionnaireId) {
        Questionnaire questionnaire =
                repository
                        .findById(questionnaireId)
                        .orElseThrow(() -> new ObjectNotFoundException("Could not find by given Id"));

        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }
}
