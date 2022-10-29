package com.fortunate.seamfix_assessment.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author fortunate on 28/10/2022
 * @project
 */

@Builder
@Data
public class BvnDetails {

    private long id;

    private String bvn;

    private String imageDetail;

    private String basicDetail;
}
