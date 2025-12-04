package com.example.banking.loan.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Client to fetch user info from Registration Service via service discovery.
 */
@Component
public class RegistrationClient {

  private final WebClient webClient;

  public RegistrationClient(WebClient.Builder builder) {
    this.webClient = builder.baseUrl("http://registration-service").build();
  }

  /** Calls Registration Service to get user by username. */
  public Mono<UserInfo> getUserByUserName(String userName) {
    return webClient.get()
        .uri("/api/v1/registration/users/{userName}", userName)
        .retrieve()
        .bodyToMono(UserInfo.class);
  }

  /** Projection of RegistrationService's UserResponse. */
  public record UserInfo(String userId, String userName, String organizationName, String emailId) {}

  @Configuration
  static class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
      return WebClient.builder();
    }
  }
}
