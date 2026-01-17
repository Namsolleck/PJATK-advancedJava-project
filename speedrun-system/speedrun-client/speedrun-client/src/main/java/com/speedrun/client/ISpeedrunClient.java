package com.speedrun.client;

import com.speedrun.client.dto.GameDto;
import java.util.List;

public interface ISpeedrunClient {
    List<GameDto> fetchGames();
}