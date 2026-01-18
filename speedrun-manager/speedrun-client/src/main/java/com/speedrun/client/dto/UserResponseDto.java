package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {
    private UserDto data;
    public UserDto getData() { return data; }
    public void setData(UserDto data) { this.data = data; }
}