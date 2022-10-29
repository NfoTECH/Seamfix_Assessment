package com.fortunate.seamfix_assessment.service.impl;

import com.fortunate.seamfix_assessment.dto.BvnDetails;
import com.fortunate.seamfix_assessment.service.BvnDetailsCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author fortunate on 28/10/2022
 * @project
 */

@Service
public class BvnDetailsCacheImpl implements BvnDetailsCache {

    public static final List<BvnDetails> BVN_DETAILS = List.of(
            composeBvnDetails("11145909999"),
            composeBvnDetails("88834932000"),
            composeBvnDetails("22284847598"),
            composeBvnDetails("84378999999"),
            composeBvnDetails("22284857889"),
            composeBvnDetails("66666666666"),
            composeBvnDetails("77777777777"),
            composeBvnDetails("32329494949"),
            composeBvnDetails("99992222211")
    );

    public static BvnDetails composeBvnDetails(String bvn) {
        return BvnDetails.builder()
                .bvn(bvn)
                .imageDetail(getDefaultBase64Image())
                .basicDetail(getDefaultBase64Image())
                .build();
    }

    public static String getDefaultBase64Image() {
        return "\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==";
    }

    @Override
    public Optional<BvnDetails> findByBvn(String bvn) {
        return BVN_DETAILS.stream()
                .filter(bvnDetail -> bvnDetail.getBvn().equals(bvn))
                .findFirst();
    }
}
