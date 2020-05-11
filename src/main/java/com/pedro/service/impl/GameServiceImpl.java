package com.pedro.service.impl;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.repository.BridgeRepository;
import com.pedro.repository.GameRepository;
import com.pedro.service.BridgeService;
import com.pedro.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BridgeRepository bridgeRepository;

    @Override
    public List<Game> findGames(String openid) {
        return gameRepository.findGames(openid);
    }

    @Override
    public int addGame(Game game) {
        //添加比赛
        gameRepository.addGame(game);
        //找到gameId
        int gameid = gameRepository.getId(game.getTitle());
        //添加sponsor关系
        bridgeRepository.hostGame(game.getSponserid(),gameid);
        //返回gameId
        return gameid;
    }

}
