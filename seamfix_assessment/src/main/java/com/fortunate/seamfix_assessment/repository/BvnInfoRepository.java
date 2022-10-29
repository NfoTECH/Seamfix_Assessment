package com.fortunate.seamfix_assessment.repository;

import com.fortunate.seamfix_assessment.entity.BvnInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface BvnInfoRepository extends MongoRepository<BvnInfoEntity, UUID> {
}
