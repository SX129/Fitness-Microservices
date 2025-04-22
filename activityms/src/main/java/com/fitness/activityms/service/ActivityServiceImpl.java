package com.fitness.activityms.service;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.dto.ActivityRequest;
import com.fitness.activityms.mapper.ActivityMapper;
import com.fitness.activityms.model.Activity;
import com.fitness.activityms.model.ActivityType;
import com.fitness.activityms.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserValidationService userValidationService) {
        this.activityRepository = activityRepository;
        this.userValidationService = userValidationService;
    }


    @Override
    public ActivityDTO getActivityById(Long userId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);

        if(activity == null){
            throw new RuntimeException("Activity id does not exist.");
        }else if(!activity.getUserId().equals(userId)){
            throw new RuntimeException("User does not contain activity id.");
        }

        return ActivityMapper.mapToActivityDTO(activity);
    }

    @Override
    public List<ActivityDTO> getAllUserActivities(Long userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream().map(activity -> ActivityMapper.mapToActivityDTO(activity)).toList();
    }

    @Override
    public List<ActivityDTO> getAllUserActivitiesByType(Long userId, String type) {
        ActivityType activityType;

        try {
            activityType = ActivityType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid activity type: " + type);
        }

        List<Activity> activities = activityRepository.findByUserId(userId).stream().filter(activity -> activity.getType().equals(activityType)).toList();
        return activities.stream().map(activity -> ActivityMapper.mapToActivityDTO(activity)).toList();
    }

    @Override
    public ActivityDTO createActivity(Long userId, ActivityRequest activityRequest) {
//        boolean isValidUser = userValidationService.validateUser(userId);

//        if(!isValidUser){
//            throw new RuntimeException("Invalid user test.");
//        }

        Activity activity = new Activity();

        activity.setUserId(userId);
        activity.setDuration(activityRequest.getDuration());
        activity.setType(activityRequest.getType());
        activity.setAdditionalMetrics(activityRequest.getAdditionalMetrics());
        activity.setCaloriesBurned(activityRequest.getCaloriesBurned());
        activity.setStartTime(activityRequest.getStartTime());

        activityRepository.save(activity);

        return ActivityMapper.mapToActivityDTO(activity);
    }

    @Override
    public ActivityDTO updateActivityById(Long userId, String activityId, Activity activity) {
        Activity updatedActivity = activityRepository.findById(activityId).orElse(null);

        if(updatedActivity == null){
            throw new RuntimeException("Activity id does not exist.");
        }else if(!updatedActivity.getUserId().equals(userId)){
            throw new RuntimeException("User does not contain activity id.");
        }

        if(activity.getAdditionalMetrics() != null){
            updatedActivity.setAdditionalMetrics(activity.getAdditionalMetrics());
        }

        if(activity.getDuration() != null){
            updatedActivity.setDuration(activity.getDuration());
        }

        if(activity.getType() != null){
            updatedActivity.setType(activity.getType());
        }

        if(activity.getCaloriesBurned() != null){
            updatedActivity.setCaloriesBurned(activity.getCaloriesBurned());
        }

        if(activity.getStartTime() != null){
            updatedActivity.setStartTime(activity.getStartTime());
        }

        updatedActivity.setUpdatedAt(LocalDateTime.now());
        activityRepository.save(updatedActivity);

        return ActivityMapper.mapToActivityDTO(updatedActivity);
    }

    @Override
    public boolean deleteActivityById(Long userId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);

        if(activity == null){
            throw new RuntimeException("Activity id does not exist.");
        }else if(!activity.getUserId().equals(userId)){
            throw new RuntimeException("User does not contain activity id.");
        }

        activityRepository.delete(activity);
        return true;
    }
}
