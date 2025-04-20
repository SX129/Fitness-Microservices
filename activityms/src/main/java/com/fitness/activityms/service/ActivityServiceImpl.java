package com.fitness.activityms.service;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.dto.RegisterRequest;
import com.fitness.activityms.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @Override
    public ActivityDTO getActivityById(Long userId, Long activityId) {
        return null;
    }

    @Override
    public List<ActivityDTO> getAllUserActivities(Long userId) {
        return null;
    }

    @Override
    public List<ActivityDTO> getAllUserActivitiesByType(Long userId, String type) {
        return null;
    }

    @Override
    public ActivityDTO createActivity(Long userId, RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public ActivityDTO updateActivityById(Long userId, Long activityId, RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public boolean deleteActivityById(Long userId, Long activityId) {
        return false;
    }
}
