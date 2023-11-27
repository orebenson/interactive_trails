package com.Team4.SmartTowns.checkpoints;

import com.Team4.SmartTowns.trails.Trail;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Checkpoint {
    private String name;
    private int[] coordinates;
    private Trail trail;

    private String description;

    public Checkpoint() {
        this.name = "";
        this.coordinates = new int[2];
        this.description = "";
        this.trail = null;
    }
}
