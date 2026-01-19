package com.speedrun.client;

import com.speedrun.client.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class SpeedrunClient {
    private static final Logger log = LoggerFactory.getLogger(SpeedrunClient.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.speedrun.com/api/v1";

    public GameDto getGameById(String gameId) {
        try {
            GameResponseDto response = restTemplate.getForObject(API_URL + "/games/" + gameId, GameResponseDto.class);
            return (response != null) ? response.getData() : null;
        } catch (Exception e) { return null; }
    }

    public List<CategoryDto> getCategoriesByGameId(String gameId) {
        try {
            CategoryResponseDto response = restTemplate.getForObject(API_URL + "/games/" + gameId + "/categories", CategoryResponseDto.class);
            return (response != null) ? response.getData() : List.of();
        } catch (Exception e) { return List.of(); }
    }

    public List<RankDto> getLeaderboard(String gameId, String categoryId, int top) {
        String url = String.format("%s/leaderboards/%s/category/%s?top=%d", API_URL, gameId, categoryId, top);
        try {
            LeaderboardResponseDto response = restTemplate.getForObject(url, LeaderboardResponseDto.class);
            return (response != null) ? response.getRanks() : List.of();
        } catch (Exception e) {
            log.warn("Nie znaleziono rankingu dla kategorii: {}", categoryId);
            return List.of();
        }
    }

    public UserDto getUserById(String userId) {
        if (userId == null) return null;
        try {
            UserResponseDto response = restTemplate.getForObject(API_URL + "/users/" + userId, UserResponseDto.class);
            return (response != null) ? response.getData() : null;
        } catch (Exception e) { return null; }
    }

    public PlatformDto getPlatformById(String platformId) {
        if (platformId == null) return null;
        String url = API_URL + "/platforms/" + platformId;
        try {
            PlatformResponseDto response = restTemplate.getForObject(url, PlatformResponseDto.class);
            return (response != null) ? response.getData() : null;
        } catch (Exception e) {
            log.error("Błąd pobierania platformy {}: {}", platformId, e.getMessage());
            return null;
        }
    }

    public String findGameIdByName(String name) {
        String url = API_URL + "/games?name=" + name;
        try {
            GameListResponseDto response = restTemplate.getForObject(url, GameListResponseDto.class);
            if (response != null && response.getData() != null && !response.getData().isEmpty()) {
                // Zwracamy ID pierwszego dopasowania
                return response.getData().get(0).getId();
            }
        } catch (Exception e) {
            log.error("Błąd podczas szukania gry o nazwie {}: {}", name, e.getMessage());
        }
        return null;
    }
}