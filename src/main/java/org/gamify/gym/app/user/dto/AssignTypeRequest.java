package org.gamify.gym.app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignTypeRequest {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
    @JsonProperty("weight")
    public Double weight;
    @JsonProperty("height")
    public Double height;
}
