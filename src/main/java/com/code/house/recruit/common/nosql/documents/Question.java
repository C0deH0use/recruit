package com.code.house.recruit.common.nosql.documents;

import com.code.house.recruit.common.nosql.documents.enums.QuestionCategory;
import com.code.house.recruit.common.nosql.documents.enums.QuestionDifficulty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Question {
    private String question;
    private String answer;
    private QuestionDifficulty difficulty;
    private QuestionCategory category;
    private String hint;

}
