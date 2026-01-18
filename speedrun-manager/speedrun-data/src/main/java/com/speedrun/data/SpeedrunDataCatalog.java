package com.speedrun.data;

import com.speedrun.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpeedrunDataCatalog {

    @Autowired
    private GameRepository games;
    @Autowired
    private PlatformRepository platforms;
    @Autowired
    private RunRepository runs;

    public GameRepository getGames() { return games; }
    public PlatformRepository getPlatforms() { return platforms; }
    public RunRepository getRuns() { return runs; }
}