package com.fortunate.seamfix_assessment.service;

import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;

public interface BvnInfoService {
    void savePayloadAsync(BvnRequest request, BvnResponse response);
}
