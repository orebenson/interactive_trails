package com.Team4.SmartTowns.progression.model;
import java.util.List;

public interface progressionRepository {
    List<progressionUser> findProgress();
}
