package com.physical.movement.controller;

import com.physical.movement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("team")
public class TeamController {

    @Autowired
    TeamService teamService;
}
