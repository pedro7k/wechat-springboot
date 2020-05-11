package com.pedro.controller;

import com.pedro.entity.Game;
import com.pedro.entity.User;
import com.pedro.repository.BridgeRepository;
import com.pedro.repository.GameRepository;
import com.pedro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Test {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BridgeRepository bridgeRepository;

    //GameRepository测试
    @PostMapping("/addGame")
    public void addGame(){
        Game game = new Game("test", "NBA", "2015-1-1", "10:00", "二号", "保龄球");
        gameRepository.addGame(game);
    }

    @GetMapping("/getId/{title}")
    public int getId(@PathVariable("title")String title){
        return gameRepository.getId(title);
    }

    @GetMapping("/findGames/{openid}")
    public List<Game> findGames(@PathVariable("openid")String openid){
        return gameRepository.findGames(openid);
    }

    //UserRepository测试
    @PostMapping("/addUser")
    public void addUser(){
        User test = new User("god","上帝","http://");
        userRepository.addUser(test);
    }

    //BridgeRepository测试
    @PostMapping("/joinGame")
    public void joinGame(){
        String openid = "god";
        int gameid = 8;
        bridgeRepository.joinGame(openid,gameid);
    }

    @PostMapping("/hostGame")
    public void hostGame(){
        String openid = "god";
        int gameid = 9;
        bridgeRepository.hostGame(openid,gameid);
    }

    @GetMapping("/isSponsor")
    public int isSponsor(){
        String openid = "god";
        int gameid = 8;
        return bridgeRepository.isSponsor(openid,gameid);
    }

    @GetMapping("/isPlayer")
    public int isPlayer(){
        String openid = "god";
        int gameid = 8;
        return bridgeRepository.isPlayer(openid,gameid);
    }

    @DeleteMapping("/cancelGameFromBridge")
    public void cancelGameFromBridge(){
        int gameid=8;
        bridgeRepository.cancelGameFromBridge(8);
    }

    @DeleteMapping("/cancelGameFromGame")
    public void cancelGameFromGame(){
        int gameid=9;
        bridgeRepository.cancelGameFromGame(gameid);
    }

    @GetMapping("/findUsers")
    public List<User> findUsers(){
        int gameid = 1;
        return bridgeRepository.findUsers(1);
    }

}
