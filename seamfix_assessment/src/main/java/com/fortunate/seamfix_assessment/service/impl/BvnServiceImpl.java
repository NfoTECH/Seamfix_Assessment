package com.fortunate.seamfix_assessment.service.impl;

import com.fortunate.seamfix_assessment.dto.BvnDetails;
import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.service.BvnDetailsCache;
import com.fortunate.seamfix_assessment.service.BvnService;
import com.fortunate.seamfix_assessment.util.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author fortunate on 28/10/2022
 * @project
 */

@Service
@RequiredArgsConstructor
public class BvnServiceImpl implements BvnService {

    private final BvnDetailsCache bvnDetailsCache;

    @Override
    public ResponseEntity<BvnResponse> validateBvn(BvnRequest request) {
        String bvn = request.getBvn();
        if (!hasValidDigitCount(bvn)) {
            return new ResponseEntity<>(getInvalidBvnResponse(bvn), HttpStatus.BAD_REQUEST);
        }
        if (!containsDigitsOnly(bvn)) {
            return new ResponseEntity<>(getInvalidBvnWithNonDigitsResponse(bvn), HttpStatus.BAD_REQUEST);
        }

        Optional<BvnDetails> optionalBvnDetails = bvnDetailsCache.findByBvn(bvn);
        if (optionalBvnDetails.isEmpty()) {
            return new ResponseEntity<>(getBvnNotFoundResponse(bvn), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getValidBvnResponse(optionalBvnDetails.get()), HttpStatus.OK);
    }

    boolean hasValidDigitCount(String bvn) {
        int validDigitCount = 11;
        return bvn.length() == validDigitCount;
    }

    private BvnResponse getInvalidBvnResponse(String bvn) {
        BvnResponse response = new BvnResponse();
        response.setMessage("The searched BVN is invalid");
        response.setCode(CustomResponseCode.INVALID);
        response.setBvn(bvn);
        return response;
    }

    boolean containsDigitsOnly(String bvn) {
        for (int i = 0; i < bvn.length(); i++) {
            char currentChar = bvn.charAt(i);
            boolean isNumber = currentChar >= '0' && currentChar <= '9';
            if (!isNumber)
                return false;
        }
        return true;
    }
    private BvnResponse getInvalidBvnWithNonDigitsResponse(String bvn) {
        BvnResponse response = new BvnResponse();
        response.setMessage("The searched BVN is invalid");
        response.setCode(CustomResponseCode.BAD_REQUEST);
        response.setBvn(bvn);
        return response;
    }

    BvnResponse getBvnNotFoundResponse(String bvn) {
        BvnResponse response = new BvnResponse();
        response.setMessage("The searched BVN does not exist");
        response.setCode(CustomResponseCode.NOT_FOUND);
        response.setBvn(bvn);
        return response;
    }

    BvnResponse getValidBvnResponse(BvnDetails bvnDetails) {
        BvnResponse response = new BvnResponse();
        response.setMessage("SUCCESS");
        response.setCode(CustomResponseCode.SUCCESS);
        response.setBvn(bvnDetails.getBvn());
        response.setBasicDetail(bvnDetails.getBasicDetail());
        response.setImageDetail(bvnDetails.getImageDetail());
        return response;
    }
}
