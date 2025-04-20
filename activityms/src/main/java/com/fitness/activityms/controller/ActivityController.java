package com.fitness.activityms.controller;

import com.fitness.activityms.dto.RegisterRequest;
import com.fitness.activityms.model.Activity;
import com.fitness.activityms.responses.ActivityResponse;
import com.fitness.activityms.service.ActivityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/{userId}/activities")
public class ActivityController {
    private final ActivityServiceImpl activityService;

    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }


    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable Long userId, @PathVariable Long activityId){

    }

    @GetMapping()
    public ResponseEntity<ActivityResponse> getAllUserActivities(@PathVariable Long userId, @RequestParam(required = false) String type){

    }

    @PostMapping()
    public ResponseEntity<ActivityResponse> createActivity(@PathVariable Long userId, @RequestBody RegisterRequest registerRequest){

    }

    @PatchMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivityById(@PathVariable Long userId, @PathVariable Long activityId, @RequestBody Activity activity){

    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> deleteActivityById(@PathVariable Long userId, @PathVariable Long activityId){

    }
}
