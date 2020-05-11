package com.pedro.utils;


import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;
import com.pedro.repository.GameRepository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    //将User的list 化成 Map的list
    public static List<Map<String, String>> listToStringForUser(List<User> list){
/*        String ret = "";
        for (User user : list) {
            ret += (user.toString()+";");
        }
        return ret;*/

        List<Map<String,String>> ret = new ArrayList<>();

        for (User user : list) {
            Map<String,String > map = new HashMap<>();
            map.put("nickname",user.getName());
            map.put("avatarurl",user.getImg());
            ret.add(map);
        }

        return ret;
    }

    //将Game的list 化成 Map的list
    public static List<Map<String, String>> listToStringForGame(List<Game> list){
/*        String ret = "";
        for (Game game : list) {
            ret += (game.toString()+";");
        }
        return ret;*/

        List<Map<String,String>> ret = new ArrayList<>();

        for (Game game : list) {
            Map<String,String> map = new HashMap<>();
            map.put("gameId", String.valueOf(game.getGameid()));
            map.put("content",game.getTitle());
            map.put("date",String.valueOf(game.getDate()));
            map.put("time",game.getTime());
            map.put("site",game.getPlace());
            map.put("name",game.getType());
            ret.add(map);
        }

        return ret;
    }

}
