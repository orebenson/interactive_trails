package com.Team4.SmartTowns.progression.model;
import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class userProgressionImpl implements progressionRepository {
private JdbcTemplate jdbc;
private RowMapper<progressionUser> progressionUserRowMapper;




}
