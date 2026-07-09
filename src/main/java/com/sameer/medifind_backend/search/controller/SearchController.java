package com.sameer.medifind_backend.search.controller;

import com.sameer.medifind_backend.search.dto.MedicineSearchResponse;
import com.sameer.medifind_backend.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<MedicineSearchResponse>> searchMedicine(
            @RequestParam String medicine) {

        return ResponseEntity.ok(
                searchService.searchByMedicine(medicine)
        );
    }
}