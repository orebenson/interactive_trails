package com.Team4.SmartTowns.medals.model;

import com.Team4.SmartTowns.profile.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MedalRepositoryImpl {

    private JdbcTemplate jdbc;
    private RowMapper<Medal> medalMapper;
}
