package com.code.house.recruit.common.testdata;

import com.code.house.recruit.data.nosql.documents.Question;
import com.code.house.recruit.data.nosql.documents.Questionnaire;
import com.code.house.recruit.data.nosql.documents.User;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public final class TestQuestionnaireData {
    private static final User user_1 = TestUserData.USER_1.build();
    private static final User user_2 = TestUserData.USER_2.build();

    public static class Questionnaire_1 {
        public static User USER = user_1;
        public static Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_1, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_2, StringUtils.EMPTY)
        );
        public static final Questionnaire entity(){
            return Questionnaire.builder()
                    .user(USER)
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }

    public static class Questionnaire_2 {
        public static User USER = user_2;
        public static Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_3, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_4, StringUtils.EMPTY)
        );
        public static final Questionnaire entity(){
            return Questionnaire.builder()
                    .user(USER)
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }

    public static class Questionnaire_3 {
        public static User USER = user_2;
        public static Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_2, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_3, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_4, StringUtils.EMPTY)
        );
        public static final Questionnaire entity(){
            return Questionnaire.builder()
                    .user(USER)
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }
}
