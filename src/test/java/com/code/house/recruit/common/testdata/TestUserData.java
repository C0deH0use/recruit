package com.code.house.recruit.common.testdata;

import com.code.house.recruit.common.nosql.documents.User;

public class TestUserData {
    public static class USER_1 {
        public static final String id = "user1";
        public static final String firstName = "Name A";
        public static final String lastName = "LastName A";

        public static final String email = "name_a@gmail.com";
        public static final User.Status status = User.Status.ACTIVE;

        public static final User build() {
            return User.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .status(status)
                    .build();
        }

    }
    public static class USER_2 {
        public static final String id = "user2";
        public static final String firstName = "Name B";
        public static final String lastName = "LastName B";
        public static final String email = "name_b@gmail.com";
        public static final User.Status status = User.Status.ACTIVE;

        public static final User build() {
            return User.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .status(status)
                    .build();
        }
    }

    public static class USER_3 {
        public static final String id = "user3";
        public static final String firstName = "Name C";
        public static final String lastName = "LastName c";

        public static final String email = "name_c@gmail.com";
        public static final User.Status status = User.Status.ACTIVE;

        public static final User build() {
            return User.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .status(status)
                    .build();
        }
    }
}
