package com.fitness.activityms.service;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.dto.ActivityRequest;
import com.fitness.activityms.model.Activity;

import java.util.List;

public interface ActivityService {
    public ActivityDTO getActivityById(Long userId, String activityId);

    public List<ActivityDTO> getAllUserActivities(Long userId);

    public List<ActivityDTO> getAllUserActivitiesByType(Long userId, String type);

    public ActivityDTO createActivity(Long userId, ActivityRequest activityRequest);

    public ActivityDTO updateActivityById(Long userId, String activityId, Activity activity);

    public boolean deleteActivityById(Long userId, String activityId);
}
