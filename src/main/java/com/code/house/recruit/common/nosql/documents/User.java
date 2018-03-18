package com.code.house.recruit.common.nosql.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
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
