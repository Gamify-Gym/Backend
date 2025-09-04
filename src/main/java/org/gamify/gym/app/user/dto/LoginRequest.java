package org.gamify.gym.app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
