package com.fitness.activityms.mapper;

import com.fitness.activityms.dto.ActivityDTO;
import com.fitness.activityms.model.Activity;

public class ActivityMapper {
    public static ActivityDTO mapToActivityDTO(Activity activity){
        ActivityDTO activityDTO = new ActivityDTO();

        activityDTO.setType(activity.getType());
        activityDTO.setDuration(activity.getDuration());
        activityDTO.setCaloriesBurned(activity.getCaloriesBurned());
        activityDTO.setStartTime(activity.getStartTime());
        activityDTO.setAdditionalMetrics(activity.getAdditionalMetrics());

        return activityDTO;
    }
}
