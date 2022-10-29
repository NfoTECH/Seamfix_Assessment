package com.fortunate.seamfix_assessment.service.impl;

import com.fortunate.seamfix_assessment.payload.request.BvnRequest;
import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.repository.BvnInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author fortunate on 28/10/2022
 * @project
 */


@ContextConfiguration(classes = {BvnInfoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BvnInfoServiceImplTest {

    @MockBean
    private BvnInfoRepository bvnInfoRepository;

    @Autowired
    private BvnInfoServiceImpl bvnInfoServiceImpl;

    @DisplayName("Test for valid BVN input and success response code 00 on save")
    @Test
    void testSavePayloadAsync() {
        BvnRequest bvnRequest = new BvnRequest("22284847598");

        BvnResponse bvnResponse = new BvnResponse();
        bvnResponse.setBasicDetail("\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==");
        bvnResponse.setBvn("22284847598");
        bvnResponse.setCode("00");
        bvnResponse.setImageDetail("\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==");
        bvnResponse.setMessage("SUCCESS");
        bvnInfoServiceImpl.savePayloadAsync(bvnRequest, bvnResponse);
        assertEquals("22284847598", bvnRequest.getBvn());
        assertEquals("\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==", bvnResponse.getBasicDetail());
        assertEquals("SUCCESS", bvnResponse.getMessage());
        assertEquals("\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==", bvnResponse.getImageDetail());
        assertEquals("00", bvnResponse.getCode());

    }
}