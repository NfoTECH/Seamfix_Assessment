package com.fortunate.seamfix_assessment.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @author fortunate on 28/10/2022
 * @project
 */


@Document(collection = "BvnInfo")
@Data
@Builder
public class BvnInfoEntity {
    @Id
    private UUID id;
    private String requestPayload;
    private String message;
    private String code;
    private String bvn;
    private String imageDetail;
    private String basicDetail;
}
