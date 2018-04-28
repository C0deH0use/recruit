package com.code.house.recruit.web.reqres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCandidateQuestionPair {

    @NotBlank
    private String questionId;
    private String answer;
}
