package com.fortunate.seamfix_assessment.controller;


import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.service.BvnInfoService;
import com.fortunate.seamfix_assessment.service.BvnService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author fortunate on 28/10/2022
 * @project
 */

@AllArgsConstructor
@RestController
@RequestMapping("/bv-service")
public class BvnController {
    private final BvnService bvnService;
    private final BvnInfoService bvnInfoService;


    @PostMapping("/svalidate/wrapper")
    public ResponseEntity<BvnResponse> validateBvn(@Valid @RequestBody BvnRequest request) {
        BvnResponse response = bvnService.validateBvn(request).getBody();
        bvnInfoService.savePayloadAsync(request, response);
        return bvnService.validateBvn(request);
    }
}