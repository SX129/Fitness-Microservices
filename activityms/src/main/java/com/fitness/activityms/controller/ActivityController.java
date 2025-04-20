package com.fitness.activityms.controller;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.dto.ActivityRequest;
import com.fitness.activityms.model.Activity;
import com.fitness.activityms.responses.ActivityResponse;
import com.fitness.activityms.service.ActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/{userId}/activities")
public class ActivityController {
    private final ActivityServiceImpl activityService;

    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }


    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable Long userId, @PathVariable Long activityId){
        try{
            ActivityDTO activityDTO = activityService.getActivityById(userId, activityId);
            return new ResponseEntity<>(new ActivityResponse(activityDTO, "User activity found."), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ActivityResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<ActivityResponse> getAllUserActivities(@PathVariable Long userId, @RequestParam(required = false) String type){
        try{
            List<ActivityDTO> activityDTOs;

            if(type == null){
                activityDTOs = activityService.getAllUserActivities(userId);

            }else{
                activityDTOs = activityService.getAllUserActivitiesByType(userId, type);

            }
            if(activityDTOs.size() == 0){
                return new ResponseEntity<>(new ActivityResponse(activityDTOs, "No activities found."), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ActivityResponse(activityDTOs, "User activities found."), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new ActivityResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<ActivityResponse> createActivity(@PathVariable Long userId, @RequestBody ActivityRequest activityRequest){
        ActivityDTO activityDTO = activityService.createActivity(userId, activityRequest);
        return new ResponseEntity<>(new ActivityResponse(activityDTO, "User activity created."), HttpStatus.CREATED);
    }

    @PatchMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivityById(@PathVariable Long userId, @PathVariable Long activityId, @RequestBody Activity activity){
        try{
            ActivityDTO activityDTO = activityService.updateActivityById(userId, activityId, activity);
            return new ResponseEntity<>(new ActivityResponse(activityDTO, "User activity updated."), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ActivityResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> deleteActivityById(@PathVariable Long userId, @PathVariable Long activityId){
        try{
            boolean activityDeleted = activityService.deleteActivityById(userId, activityId);
            return new ResponseEntity<>(new ActivityResponse(null, "User activity deleted."), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ActivityResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
