package com.sameer.medifind_backend.dashboard.controller;

import com.sameer.medifind_backend.dashboard.dto.DashboardResponse;
import com.sameer.medifind_backend.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> dashboard() {

        return ResponseEntity.ok(
                dashboardService.getDashboard()
        );
    }
}