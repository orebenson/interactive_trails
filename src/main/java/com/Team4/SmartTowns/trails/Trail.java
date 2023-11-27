package com.Team4.SmartTowns.trails;

import com.Team4.SmartTowns.checkpoints.Checkpoint;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Trail {
    private String name;
    private String location;
    private List<Checkpoint> checkpoints;

    public Trail(){
        this.name = "";
        this.location = "";
        this.checkpoints = new ArrayList<Checkpoint>();
        for(int i = 0; i < 15; i++) {
            Checkpoint checkpoint = new Checkpoint();
            checkpoint.setTrail(this);
            this.checkpoints.add(checkpoint);
        }
    }

}
