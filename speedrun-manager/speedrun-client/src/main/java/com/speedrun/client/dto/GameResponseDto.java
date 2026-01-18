package com.speedrun.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResponseDto {
    private GameDto data;
    public GameDto getData() { return data; }
    public void setData(GameDto data) { this.data = data; }
}