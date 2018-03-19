package com.code.house.recruit.common.testdata;

import com.code.house.recruit.common.nosql.documents.User;

public class TestUserData {
    public static class USER_1 {
        public static final String firstName = "Name A";
        public static final String lastName = "LastName A";

        public static final String email = "name_a@gmail.com";
        public static final User.Status status = User.Status.ACTIVE;

        public static final User build() {
            return User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .status(status)
                    .build();
        }
    }
}
