package com.fitness.activityms.dto;

import com.fitness.activityms.model.ActivityType;
import java.time.LocalDateTime;
import java.util.Map;

public class ActivityDTO {
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;

    public ActivityDTO(){}

    public ActivityDTO(ActivityType type, Integer duration, Integer caloriesBurned, LocalDateTime startTime, Map<String, Object> additionalMetrics) {
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.startTime = startTime;
        this.additionalMetrics = additionalMetrics;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Map<String, Object> getAdditionalMetrics() {
        return additionalMetrics;
    }

    public void setAdditionalMetrics(Map<String, Object> additionalMetrics) {
        this.additionalMetrics = additionalMetrics;
    }
}
