package com.fortunate.seamfix_assessment.service;

import com.fortunate.seamfix_assessment.dto.BvnDetails;

import java.util.Optional;

public interface BvnDetailsCache {
    Optional<BvnDetails> findByBvn (String bvn);
}
