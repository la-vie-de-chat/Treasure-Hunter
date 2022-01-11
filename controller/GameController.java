package com.laioffer.jupiter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

//在这个class上面标记@Controller，电脑就可以自动判断这个类是Controller
@Controller
public class GameController {
    // fetch data from url
    // 方法一：用于搜索
    // /game?game_name=war%20xxx --> 搜索特定游戏
    // /game                     --> 搜索任意游戏
    @RequestMapping(value = "/game", method = RequestMethod.GET)

    // 方法二：用于特定参数
    // 用 @RequestParam 去找url中对应的key
    // for example: http://localhost:8080/search?lon=22&lat=37
    // public String search(@RequestParam("lon") double lon, @RequestParam("lat") double lat) (...)
    public void getGame(@RequestParam("game_name") String gameName, HttpServletResponse response) {
    }

}
