package com.fitness.activityms.service;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.dto.RegisterRequest;

import java.util.List;

public interface ActivityService {
    public ActivityDTO getActivityById(Long userId, Long activityId);

    public List<ActivityDTO> getAllUserActivities(Long userId);

    public List<ActivityDTO> getAllUserActivitiesByType(Long userId, String type);

    public ActivityDTO createActivity(Long userId, RegisterRequest registerRequest);

    public ActivityDTO updateActivityById(Long userId, Long activityId, RegisterRequest registerRequest);

    public boolean deleteActivityById(Long userId, Long activityId);
}
