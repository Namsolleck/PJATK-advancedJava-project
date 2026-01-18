package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String id;
    private NamesDto names;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public NamesDto getNames() { return names; }
    public void setNames(NamesDto names) { this.names = names; }
}