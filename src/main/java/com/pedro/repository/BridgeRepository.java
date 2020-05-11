package com.pedro.repository;

import com.pedro.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BridgeRepository {
    //非举办者加入比赛
    public void joinGame(String openid, int gameid);

    //举办者加入比赛
    public void hostGame(String openid,int gameid);

    //通过openid和gameid判断是否是sponsor(已知是比赛参与者的情况下)
    public int isSponsor(String openid,int gameid);

    //通过openid和gameid判断是否是比赛参与者
    public int isPlayer(String openid,int gameid);

    //从bridge中删除比赛
    public void cancelGameFromBridge(int gameid);

    //从game中删除比赛
    public void cancelGameFromGame(int gameid);

    //用户点击一场比赛，返回所有User
    public List<User> findUsers(int gameid);
}
