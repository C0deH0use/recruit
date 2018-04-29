package com.code.house.recruit.data.nosql.documents;

import com.code.house.recruit.data.nosql.documents.enums.QuestionCategory;
import com.code.house.recruit.data.nosql.documents.enums.QuestionDifficulty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Question {

    @Id
    @Indexed
    private String id;
    private String question;
    private String answer;
    private QuestionDifficulty difficulty;
    private QuestionCategory category;
    private String hint;

}
