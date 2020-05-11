package com.pedro.controller;

import com.pedro.entity.User;
import com.pedro.service.UserService;
import com.pedro.utils.Json;
import com.pedro.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/wx")
    public void getLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            String code = request.getParameter("code");
            String OpenId = Token.getOpenId(code);
            //create a session
            HttpSession session = request.getSession();
            session.setAttribute("openId",OpenId);
            System.out.println("已设置openId属性");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(session.getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/wxUserInfo")
    protected void addUser(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            request.setCharacterEncoding("utf-8");
            String OpenId = (String)session.getAttribute("openId");
            if (OpenId == "" || OpenId == null)
                return;
            String rawData = request.getParameter("rawData");
            String nickName = Json.getValueFromJson(rawData, "nickName");
            String avatarUrl = Json.getValueFromJson(rawData, "avatarUrl");
            //System.out.println("用户信息" + nickName + " " + avatarUrl);
            //应将此数据存入数据库
            User user = new User(OpenId, nickName, avatarUrl);
            System.out.println(this.getClass().getName()+" : "+user.toString());
            /*UserRepositoryImpl userRepository = new UserRepositoryImpl();
            userRepository.addUser(user);*/
            userService.addUser(user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
