package com.fortunate.seamfix_assessment.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BvnResponse {
    private String message;
    private String code;
    private String bvn;
    private String imageDetail;
    private String basicDetail;
}
