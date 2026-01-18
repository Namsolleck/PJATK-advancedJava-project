package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaderboardResponseDto {
    private LeaderboardData data;

    public List<RankDto> getRanks() {
        return (data != null) ? data.getRuns() : List.of();
    }

    public void setData(LeaderboardData data) { this.data = data; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LeaderboardData {
        private List<RankDto> runs;
        public List<RankDto> getRuns() { return runs; }
        public void setRuns(List<RankDto> runs) { this.runs = runs; }
    }
}