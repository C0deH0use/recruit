package com.code.house.recruit.data.nosql.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Questionnaire implements Persistable<String> {

    @Id
    @Indexed
    private String id;
    @CreatedDate
    private DateTime createdDate;
    private User user;
    private String additionalNotes;

    @Builder.Default
    @SuppressWarnings("PMD.ImmutableField")
    private Set<ImmutablePair<Question, String>> candidateQuestions = new HashSet();

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
