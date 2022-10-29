package com.fortunate.seamfix_assessment.service;

import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import org.springframework.http.ResponseEntity;

public interface BvnService {
    ResponseEntity<BvnResponse> validateBvn(BvnRequest request);
}
