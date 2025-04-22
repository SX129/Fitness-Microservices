package com.fitness.activityms.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public UserValidationService(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }

    public boolean validateUser(Long userId){
        try {
            return userServiceWebClient.get()
                    .uri("/v1/users/{userId}/validate", userId)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        // Log the error status code
                        System.out.println("Error Status: " + clientResponse.statusCode());
                        return clientResponse.createException().flatMap(Mono::error);
                    })
                    .bodyToMono(Boolean.class)
                    .doOnTerminate(() -> System.out.println("Request Completed"))
                    .block();
        } catch (WebClientResponseException e) {
            System.out.println("Exception: " + e.getMessage());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("User not found.");
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid request.");
            }
            return false;
        }
    }
}
