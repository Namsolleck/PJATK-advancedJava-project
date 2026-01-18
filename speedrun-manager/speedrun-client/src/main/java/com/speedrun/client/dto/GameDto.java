package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    private String id;
    private NamesDto names;
    private List<String> platforms; // Lista ID platform z Twojego pliku txt

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public NamesDto getNames() { return names; }
    public void setNames(NamesDto names) { this.names = names; }
    public List<String> getPlatforms() { return platforms; }
    public void setPlatforms(List<String> platforms) { this.platforms = platforms; }
}