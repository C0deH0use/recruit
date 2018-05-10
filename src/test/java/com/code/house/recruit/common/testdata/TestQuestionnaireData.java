package com.code.house.recruit.common.testdata;

import com.code.house.recruit.common.testdata.TestUserData.USER_1;
import com.code.house.recruit.common.testdata.TestUserData.USER_2;
import com.code.house.recruit.data.nosql.documents.Question;
import com.code.house.recruit.data.nosql.documents.Questionnaire;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public final class TestQuestionnaireData {

    private TestQuestionnaireData() {
    }

    public static class Questionnaire_1 {
        public static final Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_1, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_2, StringUtils.EMPTY)
        );

        private Questionnaire_1() {
        }

        public static Questionnaire entity() {
            return Questionnaire.builder()
                    .user(USER_1.build())
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }

    public static class Questionnaire_2 {

        public static Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_3, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_4, StringUtils.EMPTY)
        );

        private Questionnaire_2() {
        }

        public static Questionnaire entity() {
            return Questionnaire.builder()
                    .user(USER_2.build())
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }

    public static class Questionnaire_3 {

        public static final Set<ImmutablePair<Question, String>> candidateQuestions = Sets.newHashSet(
                ImmutablePair.of(TestQuestionData.QUESTION_2, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_3, StringUtils.EMPTY),
                ImmutablePair.of(TestQuestionData.QUESTION_4, StringUtils.EMPTY)
        );

        private Questionnaire_3() {
        }

        public static Questionnaire entity() {
            return Questionnaire.builder()
                    .user(USER_2.build())
                    .candidateQuestions(candidateQuestions)
                    .build();
        }
    }
}
