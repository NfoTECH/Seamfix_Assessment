package com.fortunate.seamfix_assessment.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BvnRequest {
    @NotBlank(message = "One or more of your request parameters failed validation. Please retry")
    private String bvn;
}