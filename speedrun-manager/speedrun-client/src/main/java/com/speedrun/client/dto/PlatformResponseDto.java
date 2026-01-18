package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformResponseDto {
    private PlatformDto data;
    public PlatformDto getData() { return data; }
    public void setData(PlatformDto data) { this.data = data; }
}