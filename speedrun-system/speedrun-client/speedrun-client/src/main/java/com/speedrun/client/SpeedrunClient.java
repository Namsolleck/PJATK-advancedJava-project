package com.speedrun.client;

import com.speedrun.client.dto.GameDto;
import com.speedrun.client.dto.GameResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeedrunClient implements ISpeedrunClient {

   private final RestTemplate restTemplate = new RestTamplate();
   private static final String API_URL ="https://www.speedrun.com/api/v1/games?limit=10";

   @Override
    public Listt<GameDto> fetchGames(){
       GameResponseDto response = restTemplate.getForObject(API_URL, GameResponseDto.class);
       return response != null ? response.getData() : List.of();
   }
}