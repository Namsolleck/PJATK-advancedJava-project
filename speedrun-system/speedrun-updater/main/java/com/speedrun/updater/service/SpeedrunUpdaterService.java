package com.speedrun.updater.service;

import com.speedrun.client.ISpeedrunClient;
import com.speedrun.client.dto.GameDto;
import com.speedrun.data.SpeedrunDataCatalog;
import com.speedrun.data.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeedrrunUpdaterService{

    private final ISpeedrunClient speedrunClient;
    private final SpeedrunDataCatalog dataCatalog;

    @Transactional
    public void updateGames(){
        List<GameDto> gamesFromApi=speedrunClient.fetchGames();

        for(GameDto dto : gamesFromApi){
            if (dataCatalog.getGames().findByExternalId(dto.getId()).isEmpty()){
                Game newGame = new Game();
                newGame.setExternalId(dto.getId());
                newGame.setName(dto.getNames().getInternational());

                dataCatalog.getGames().save(newGame);
                System.out.println("Zapisano nową grę: " + newGame.getName());
            }
        }
    }
}