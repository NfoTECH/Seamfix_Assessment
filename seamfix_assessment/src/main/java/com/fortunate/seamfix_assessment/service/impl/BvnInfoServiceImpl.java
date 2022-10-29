package com.fortunate.seamfix_assessment.service.impl;


import com.fortunate.seamfix_assessment.entity.BvnInfoEntity;
import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.repository.BvnInfoRepository;
import com.fortunate.seamfix_assessment.service.BvnInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


/**
 * @author fortunate on 28/10/2022
 * @project
 */

@Service
@AllArgsConstructor
public class BvnInfoServiceImpl implements BvnInfoService {
    private final BvnInfoRepository bvnInfoRepository;


    public void savePayloadAsync(BvnRequest request, BvnResponse response) {
        CompletableFuture.runAsync(() -> {
            BvnInfoEntity details = BvnInfoEntity.builder()
                    .id(UUID.randomUUID())
                    .requestPayload(request.toString())
                    .message(response.getMessage())
                    .code(response.getCode())
                    .bvn(response.getBvn())
                    .imageDetail(response.getImageDetail())
                    .basicDetail(response.getBasicDetail())
                    .build();
            bvnInfoRepository.save(details);
        });
    }
}
