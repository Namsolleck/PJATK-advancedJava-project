package com.speedrun.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NamesDto {
    private String international;
    public String getInternational() { return international; }
    public void setInternational(String international) { this.international = international; }
}