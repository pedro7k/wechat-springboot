package com.pedro.service;

import com.pedro.entity.CheckResult;

public interface BridgeService {
    public void cancleGame(int gameid);

    public CheckResult checkGame(String openid, int gameid);

    public void joinGame(String openid,int gameid);
}
