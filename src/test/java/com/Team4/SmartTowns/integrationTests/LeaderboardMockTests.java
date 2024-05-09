package com.Team4.SmartTowns.integrationTests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LeaderboardMockTests {
    @Autowired
    private MockMvc mockMvc; //bypasses spring http interface

    // integration test - full container, testing h2 database, with mockmvc
    @Test
    public void testLeaderboardWithDB() throws Exception {
        // create a users and checkpoints through the defaults tester  (which accesses the db)
        this.mockMvc.perform(get("/test")
            .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN")))
            .andDo(print())
            .andExpect(status().is(302));

        // get the /leaderboard and check that a user is there - tests the db
        this.mockMvc.perform(get("/leaderboard"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("alice")));

        
    }
}
