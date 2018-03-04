package com.code.house.recruit.common.nosql.documents;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@Document
public class CandidateQuestionnaire {

    @Id
    private String id;
    private Date date;
    private String additionalNotes;
    private Set<ImmutablePair<Question, String>> candidateQuestions;
}

