package com.physical.movement.controller;

import com.physical.movement.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;
}
