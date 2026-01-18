package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankDto {
    private RunDto run;
    public RunDto getRun() { return run; }
    public void setRun(RunDto run) { this.run = run; }
}