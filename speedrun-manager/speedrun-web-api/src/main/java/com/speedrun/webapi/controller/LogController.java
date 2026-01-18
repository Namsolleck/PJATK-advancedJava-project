package com.speedrun.webapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final String LOG_FILE_PATH = "logs/speedrun.log";

    @GetMapping
    public String getAppLogs() {
        try {
            Path path = Paths.get(LOG_FILE_PATH);

            if (Files.exists(path)) {
                // Czytamy zawartość pliku jako String
                // Zwracamy w znaczniku <pre>, żeby zachować formatowanie w przeglądarce
                String content = Files.readString(path);
                return "<pre>" + content + "</pre>";
            } else {
                return "Plik z logami jeszcze nie istnieje.";
            }
        } catch (IOException e) {
            return "Błąd podczas odczytu logów: " + e.getMessage();
        }
    }
}