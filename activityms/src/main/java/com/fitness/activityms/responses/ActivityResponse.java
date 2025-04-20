package com.fitness.activityms.responses;

import com.fitness.activityms.dto.ActivityDTO;

public class ActivityResponse {
    private ActivityDTO activityDTO;
    private String message;

    public ActivityResponse(ActivityDTO activityDTO, String message) {
        this.activityDTO = activityDTO;
        this.message = message;
    }

    public ActivityDTO getActivityDTO() {
        return activityDTO;
    }

    public void setActivityDTO(ActivityDTO activityDTO) {
        this.activityDTO = activityDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
