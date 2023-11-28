package com.Team4.SmartTowns.profile;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;

import java.util.List;

public class Profile {
    private String username;
    private List<Checkpoint> checkpoints;


    public Profile(String username, List<Checkpoint> checkpoints) {
        this.username = username;
        this.checkpoints = checkpoints;
    }

    public String getUsername() {
        return username;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
