package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameDto{
    private String id; //external id

    @JsonProperty("names")
    private GameNames names;

    @Data
    public static class GameNames {
        private String international;
    }
}