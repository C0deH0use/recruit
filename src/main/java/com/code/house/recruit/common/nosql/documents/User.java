package com.code.house.recruit.common.nosql.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class User {

    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Status status;


    public enum Status {
        ACTIVE,
        DISABLED
    }
}
