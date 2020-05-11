package com.pedro.service;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;

import java.util.List;

public interface GameService {
    public List<Game> findGames(String openid);

    public int addGame(Game game);

}
