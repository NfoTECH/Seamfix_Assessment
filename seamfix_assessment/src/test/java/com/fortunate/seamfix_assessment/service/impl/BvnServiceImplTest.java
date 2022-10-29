package com.fortunate.seamfix_assessment.service.impl;

import com.fortunate.seamfix_assessment.dto.BvnDetails;
import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.service.BvnDetailsCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author fortunate on 28/10/2022
 * @project
 */

@ContextConfiguration(classes = {BvnServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BvnServiceImplTest {

    @MockBean
    private BvnDetailsCache bvnDetailsCache;

    @Autowired
    private BvnServiceImpl bvnServiceImpl;

    @DisplayName("Test for valid BVN and retrun 02 response code, if invalid")
    @Test
    void testValidateBvn() {
        ResponseEntity<BvnResponse> actualValidateBvnResult = bvnServiceImpl.validateBvn(new BvnRequest("Bvn"));
        assertTrue(actualValidateBvnResult.hasBody());


        assertEquals(HttpStatus.BAD_REQUEST, actualValidateBvnResult.getStatusCode());
        BvnResponse body = actualValidateBvnResult.getBody();
        assertEquals("Bvn", body.getBvn());
        assertEquals("The searched BVN is invalid", body.getMessage());
        assertEquals("02", body.getCode());
    }

    @DisplayName("Test for valid digit count of 11")
    @Test
    void testHasValidDigitCount() {
        assertFalse(bvnServiceImpl.hasValidDigitCount("Bvn"));
        assertTrue(bvnServiceImpl.hasValidDigitCount("11131151819"));
    }


   @DisplayName("Should check for only digits in BVN")
    @Test
    void testContainsOnlyDigits() {
        assertFalse(bvnServiceImpl.containsDigitsOnly("Bvn"));
        assertTrue(bvnServiceImpl.containsDigitsOnly("42"));
    }

    @DisplayName("Should return a 02 reponse code when searched BVN is invalid")
    @Test
    void testValidateBvn4() {
        BvnRequest bvnRequest = mock(BvnRequest.class);
        when(bvnRequest.getBvn()).thenReturn("Bvn");
        ResponseEntity<BvnResponse> actualValidateBvnResult = bvnServiceImpl.validateBvn(bvnRequest);
        assertTrue(actualValidateBvnResult.hasBody());
        assertTrue(actualValidateBvnResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualValidateBvnResult.getStatusCode());
        BvnResponse body = actualValidateBvnResult.getBody();
        assertEquals("Bvn", body.getBvn());
        assertEquals("The searched BVN is invalid", body.getMessage());
        assertEquals("02", body.getCode());
        verify(bvnRequest).getBvn();
    }

    @DisplayName("Should return a 01 response code when BVN is not found in the cache")
    @Test
    void testGetBvnNotFoundResponse() {
        BvnResponse actualBvnNotFoundResponse = bvnServiceImpl.getBvnNotFoundResponse("Bvn");
        assertEquals("The searched BVN does not exist", actualBvnNotFoundResponse.getMessage());
        assertEquals("01", actualBvnNotFoundResponse.getCode());
        assertEquals("Bvn", actualBvnNotFoundResponse.getBvn());
    }

    @DisplayName("Should get valid BVN details from cache")
    @Test
    void testGetValidBvnResponse() {
        BvnDetails bvnDetails = mock(BvnDetails.class);
        when(bvnDetails.getBasicDetail()).thenReturn("Basic Detail");
        when(bvnDetails.getBvn()).thenReturn("Bvn");
        when(bvnDetails.getImageDetail()).thenReturn("Image Detail");
        BvnResponse actualValidBvnResponse = bvnServiceImpl.getValidBvnResponse(bvnDetails);
        assertEquals("Basic Detail", actualValidBvnResponse.getBasicDetail());
        assertEquals("SUCCESS", actualValidBvnResponse.getMessage());
        assertEquals("Image Detail", actualValidBvnResponse.getImageDetail());
        assertEquals("00", actualValidBvnResponse.getCode());
        assertEquals("Bvn", actualValidBvnResponse.getBvn());
        verify(bvnDetails).getBasicDetail();
        verify(bvnDetails).getBvn();
        verify(bvnDetails).getImageDetail();
    }
}