package com.speedrun.client;

import com.speedrun.client.dto.GameDto;
import com.speedrun.client.dto.GameResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpeedrunClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.speedrun.com/api/v1";

    public GameDto getGameById(String gameId) {
        String url = API_URL + "/games/" + gameId;
        GameResponseDto response = restTemplate.getForObject(url, GameResponseDto.class);
        return (response != null) ? response.getData() : null;
    }
}