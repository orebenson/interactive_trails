package com.Team4.SmartTowns.unitTests;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CheckpointUnitTest {

    double[] coordinates = {11.11, 22.22};

    // simple unit test - check that the checkpoint class is created properly with getters and setters
    @Test
    public void checkpointTest() {
        Checkpoint testCheckpoint = new Checkpoint(10L, "my checkpoint", coordinates, "my checkpoint description");
        assert testCheckpoint.getId().equals(10L);
        assert testCheckpoint.getName().equals("my checkpoint");
        assert Arrays.equals(testCheckpoint.getCoordinates(), coordinates);
        assert testCheckpoint.getDescription().equals("my checkpoint description");

    }
}
