package com.speedrun.data;

import com.speedrun.data.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //automatyczne wstrzykiwanie repozytooria przez konstruktor
public class SpeedrunDataCatalog{

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final PlatformRepository platformRepository;
    private final RunnerRepository runnerRepository;
    private final RunRepository runRepository;

    public GameRepository getGame(){return gameRepository;}
    public CategoryRepository getCategories() { return categoryRepository; }
    public PlatformRepository getPlatforms() { return platformRepository; }
    public RunnerRepository getRunners() { return runnerRepository; }
    public RunRepository getRuns() { return runRepository; }
}