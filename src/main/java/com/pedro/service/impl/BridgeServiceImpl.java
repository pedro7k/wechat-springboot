package com.pedro.service.impl;

import com.pedro.entity.CheckResult;
import com.pedro.entity.User;
import com.pedro.repository.BridgeRepository;
import com.pedro.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BridgeServiceImpl implements BridgeService {
    @Autowired
    private BridgeRepository bridgeRepository;

    @Override
    public void cancleGame(int gameid) {
        bridgeRepository.cancelGameFromGame(gameid);
        bridgeRepository.cancelGameFromBridge(gameid);
    }

    @Override
    public CheckResult checkGame(String openid, int gameid) {
        int isSponsor = 0;
        boolean player;
        boolean host;

        List<User> users = bridgeRepository.findUsers(gameid);

        int isPlayer = bridgeRepository.isPlayer(openid, gameid);

        if (isPlayer == 1){
            isSponsor = bridgeRepository.isSponsor(openid, gameid);
        }

        if (isPlayer == 0){
            player = false;
        }else{
            player = true;
        }

        if (isSponsor == 0){
            host = false;
        }else{
            host = true;
        }

        return new CheckResult(users,host,player);
    }

    @Override
    public void joinGame(String openid, int gameid) {
        bridgeRepository.joinGame(openid,gameid);
    }

}
