package com.code.house.recruit.common.testdata;

import com.code.house.recruit.data.nosql.documents.Question;
import com.code.house.recruit.data.nosql.documents.enums.QuestionCategory;
import com.code.house.recruit.data.nosql.documents.enums.QuestionDifficulty;

public class TestQuestionData {
    public static Question QUESTION_1 = Question.builder()
            .category(QuestionCategory.IT_BASICS)
                .difficulty(QuestionDifficulty.Junior)
                .question("Question_String")
                .answer("Answer for question")
                .hint("Question hints")
                .build();

    public static Question QUESTION_2 = Question.builder()
            .category(QuestionCategory.JAVA)
            .difficulty(QuestionDifficulty.Junior)
            .question("Question_String_ 2")
            .answer("Answer for question 2")
            .hint("Question hints")
            .build();

    public static Question QUESTION_3 = Question.builder()
            .category(QuestionCategory.FRAMEWORKS)
            .difficulty(QuestionDifficulty.Middle)
            .question("Question_String 33")
            .answer("Answer for question 3")
            .hint("Question hints")
            .build();

    public static Question QUESTION_4 = Question.builder()
            .category(QuestionCategory.JAVA)
            .difficulty(QuestionDifficulty.Senior)
            .question("Question_String 4")
            .answer("Answer for question 4")
            .hint("Question hints 4")
            .build();
}
