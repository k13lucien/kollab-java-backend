package com.kollab.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @GetMapping("/teams")
    public String getTeams() {
        return "List of teams";
    }
}
