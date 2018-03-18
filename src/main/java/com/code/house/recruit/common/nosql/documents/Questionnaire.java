package com.code.house.recruit.common.nosql.documents;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@Document
public class Questionnaire implements Persistable<String> {

    @Id
    @Indexed
    private String id;
    @CreatedDate
    private DateTime createdDate;
    private User user;
    private String additionalNotes;
    @Builder.Default
    private Set<ImmutablePair<Question, String>> candidateQuestions = new HashSet();

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}

