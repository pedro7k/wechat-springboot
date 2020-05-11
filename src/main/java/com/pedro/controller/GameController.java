package com.pedro.controller;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;
import com.pedro.service.GameService;
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
import java.util.Map;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/wxRelevantGame")
    protected void wxRelevantGame(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            request.setCharacterEncoding("utf-8");
            String OpenId = (String) session.getAttribute("openId");
            if (OpenId == "" || OpenId == null)
                return;
            //数据库查询与OpenId相关的比赛信息
            System.out.println(this.getClass().getName() + " : " + "openid=" + OpenId);
            /*GameRepositoryImpl gameRepository = new GameRepositoryImpl();
            List<Game> games = gameRepository.findGames(OpenId);*/
            List<Game> games = gameService.findGames(OpenId);

            System.err.println(this.getClass().getName() + " : " + games);
            //待处理：JSON格式转换
            List<Map<String, String>> gameMaps = Utils.listToStringForGame(games);
            String json = Json.toJson(gameMaps, "games");
            System.out.println(this.getClass().getName() + " : " + json);
            response.setContentType("text/html;charset=utf-8");
            //        String str_sql = "\"gameId\":\"sdf456456safwer3443\",\"date\":\"2019-01-01\",\"time\":\"12:00\",\"site\":\"体育馆\",\"sponsor\":\"xxx\";" +
            //                "\"gameId\":\"3443lkjollllll33\",\"date\":\"2039-01-01\",\"time\":\"16:00\",\"site\":\"内定场地哦\",\"sponsor\":\"wx\";";
            //        String json = Json.toJson(str_sql,"gameId","games");
            //System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/wxGameInfo")
    protected void addGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到GET请求");
        request.setCharacterEncoding("utf-8");
        /*Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String element = parameterNames.nextElement();
            System.out.println(element +": "+request.getParameter(element));
        }*/
        //获取比赛信息和发起人的openid
        HttpSession session = request.getSession();
        String OpenId = (String) session.getAttribute("openId");
        if (OpenId == "" || OpenId == null)
            return;
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String site = request.getParameter("site");
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        System.out.println("提交的比赛信息： ");
        System.out.println(OpenId + " " + date + " " + time + " " + site + " " + name + " " + content);

        //addGame to DB
        Game newGame = new Game(OpenId, content, date, time, site, name);
        /*GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        int gameid = gameRepository.addGame(newGame);*/
        int gameid = gameService.addGame(newGame);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(Integer.toString(gameid));
    }



}
