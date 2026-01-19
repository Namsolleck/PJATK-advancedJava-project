package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameListResponseDto {
    private List<GameDto> data;
    public List<GameDto> getData() { return data; }
    public void setData(List<GameDto> data) { this.data = data; }
}