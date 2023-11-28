package com.Team4.SmartTowns.profile;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;

import java.util.List;

public class Profile {
    private String username;
//    private  String password;
//    private  boolean loginStatus;
    private  String registerDate;
    private List<Checkpoint> checkpoints;


    public Profile(String username, List<Checkpoint> checkpoints) {
        this.username = username;
        this.checkpoints = checkpoints;
    }


    public boolean  verifyLogin() {
        //login verification logic
    }

    public String getUsername() {
        return username;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
