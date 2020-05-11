package com.pedro.controller;

import com.pedro.entity.CheckResult;
import com.pedro.entity.User;
import com.pedro.service.BridgeService;
import com.pedro.utils.Json;
import com.pedro.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class BridgeController {
    @Autowired
    private BridgeService bridgeService;

    @GetMapping("/wxShowGameInfo")
    public void wxShowGameInfo(HttpServletRequest request, HttpServletResponse response){
        try {
            String gameId = request.getParameter("gameId");
            request.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();
            String OpenId = (String)session.getAttribute("openId");
            if (OpenId == "" || OpenId == null)
                return;
            //把gameId放到数据库中查询
            /*GameRepositoryImpl gameRepository = new GameRepositoryImpl();
            //System.out.println(this.getClass().getName()+" : "+gameId + " "+Integer.parseInt(gameId));
            CheckResult checkResult = gameRepository.checkGame(OpenId, Integer.parseInt(gameId));*/
            CheckResult checkResult = bridgeService.checkGame(OpenId, Integer.parseInt(gameId));


            List<User> users = checkResult.getUsers();
            int i = 0;
            //System.out.println(this.getClass().getName()+": gameId: "+gameId+" uesrsNum: "+users.size());
            boolean isSponsor = checkResult.isIshost();
            boolean isSignedUp = checkResult.isIsplayer();

            //返回信息包括参与者们的昵称和头像，本人是否为发起者，不是的话是否已报名
            //待处理：JSON转换
            String json = Json.toJson(Utils.listToStringForUser(users),"participants");
            //yingai fanhui users , but qianduan can't receive

            json = Json.addToJson(json,"isSponser",Boolean.toString(isSponsor));
            json = Json.addToJson(json,"isSignedUp",Boolean.toString(isSignedUp));
            System.out.println(this.getClass().getName()+" : "+json);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/wxCancelGame")
    protected void cancelGame(HttpServletRequest request, HttpServletResponse response){
        //who要取消 which比赛
        String gameId = request.getParameter("gameId");
        HttpSession session = request.getSession();
        String OpenId = (String)session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        //应数据库中验证
        /*BridgeRepositoryImpl bridgeRepository = new BridgeRepositoryImpl();
        bridgeRepository.cancelGame(Integer.parseInt(gameId));*/
        bridgeService.cancleGame(Integer.parseInt(gameId));
    }

    @GetMapping("/wxSignUpGame")
    protected void signUpGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //who 要报名 which比赛
        String gameId = request.getParameter("gameId");
        HttpSession session = request.getSession();
        String OpenId = (String) session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        //应数据库中关联
        /*BridgeRepositoryImpl bridgeRepository = new BridgeRepositoryImpl();
        bridgeRepository.joinGame(OpenId,Integer.parseInt(gameId));*/
        bridgeService.joinGame(OpenId,Integer.parseInt(gameId));
    }
}
