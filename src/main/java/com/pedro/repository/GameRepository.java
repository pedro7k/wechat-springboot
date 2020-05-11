package com.pedro.repository;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository {
    //添加比赛
    public void addGame(Game game);

    //通过title找到gameId
    public int getId(String title);

    //查找用户的所有比赛
    public List<Game> findGames(String openid);
}
