package com.laioffer.jupiter.controller;

import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.entity.request.FavoriteRequestBody;
import com.laioffer.jupiter.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class FavoriteController {
    //The @Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished.
    //The @Autowired annotation can be used to autowire bean on the setter method just like @Required annotation,
    //constructor, a property or methods with arbitrary names and/or multiple arguments.
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    public void setFavoriteItem(@RequestBody FavoriteRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ;
        }
        String userId = (String) session.getAttribute("user_id");
        favoriteService.setFavoriteItem(userId, requestBody.getFavoriteItem());
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.DELETE)
    public void unsetFavoriteItem(@RequestBody FavoriteRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String userId = (String) session.getAttribute("user_id");
        favoriteService.unsetFavoriteItem(userId, requestBody.getFavoriteItem().getId());
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Item>> getFavoriteItem(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new HashMap<>();
        }
        String userId = (String) session.getAttribute("user_id");
        return favoriteService.getFavoriteItems(userId);
    }
}


