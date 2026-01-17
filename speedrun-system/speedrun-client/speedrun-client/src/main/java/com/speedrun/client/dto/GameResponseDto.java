package com.speedrun.client.dto;

import lombok.Data;
import java.util.List;

@Data
public class GameResponseDto {
    private List<GameDto> data;
}