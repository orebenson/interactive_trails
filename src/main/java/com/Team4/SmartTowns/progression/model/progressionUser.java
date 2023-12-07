package com.Team4.SmartTowns.progression.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Data
@AllArgsConstructor
public class progressionUser {
    private String username;
    private int count;

    public progressionUser(){
        this.username = "";
        this.count = 0;
    }
}
