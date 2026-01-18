package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunDto {
    private String id;
    private Map<String, Object> times;
    private List<Map<String, String>> players;
    private String platformId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @JsonProperty("times")
    public void setTimes(Map<String, Object> times) { this.times = times; }

    @JsonProperty("players")
    public void setPlayers(List<Map<String, String>> players) { this.players = players; }

    @JsonProperty("system")
    private void unpackSystem(Map<String, Object> system) {
        if (system != null) {
            this.platformId = (String) system.get("platform");
        }
    }

    public double getPrimaryTime() {
        if (times != null && times.get("primary_t") != null) {
            return Double.parseDouble(times.get("primary_t").toString());
        }
        return 0;
    }

    public String getFirstPlayerId() {
        if (players != null && !players.isEmpty()) {
            return players.get(0).get("id"); // Może być null dla gości (rel: guest)
        }
        return null;
    }

    public String getPlatformId() { return platformId; }
}