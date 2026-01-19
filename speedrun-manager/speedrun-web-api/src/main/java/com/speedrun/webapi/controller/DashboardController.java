package com.speedrun.webapi.controller;

import com.speedrun.client.SpeedrunClient;
import com.speedrun.data.model.Game;
import com.speedrun.data.repositories.GameRepository;
import com.speedrun.data.repositories.PlatformRepository;
import com.speedrun.data.repositories.UserRepository;
import com.speedrun.updater.service.SpeedrunUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict; // NAPRAWIONY IMPORT
import org.springframework.cache.annotation.Cacheable;  // NAPRAWIONY IMPORT
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired private GameRepository gameRepository;
    @Autowired private PlatformRepository platformRepository;
    @Autowired private UserRepository userRepository;

    @Autowired private SpeedrunUpdaterService updaterService;
    @Autowired private SpeedrunClient speedrunClient;

    // Główna strona ze statystykami i listą gier
    @GetMapping
    @Cacheable("gamesList")
    public String showDashboard(Model model) {
        // Lista gier
        model.addAttribute("games", gameRepository.findAll());

        // Statystyki (Top 5)
        model.addAttribute("topPlatforms", platformRepository.findMostPopularPlatforms(PageRequest.of(0, 5)));
        model.addAttribute("topUsers", userRepository.findMostActiveUsers(PageRequest.of(0, 5)));

        return "dashboard";
    }

    // Dodawanie gry (czyści cache)
    @PostMapping("/add")
    @CacheEvict(value = "gamesList", allEntries = true)
    public String addGame(@RequestParam String gameInput) {
        String gameId = speedrunClient.findGameIdByName(gameInput);
        if (gameId == null) {
            gameId = gameInput;
        }
        updaterService.updateGame(gameId);
        return "redirect:/dashboard";
    }

    // Szczegóły gry (Kategorie i Top Runy)
    @GetMapping("/game/{id}")
    public String showGameDetails(@PathVariable Long id, Model model) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));

        model.addAttribute("game", game);
        return "game-details";
    }
}