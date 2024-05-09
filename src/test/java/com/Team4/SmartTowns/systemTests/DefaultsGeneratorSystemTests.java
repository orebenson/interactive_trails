package com.Team4.SmartTowns.systemTests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultsGeneratorSystemTests {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // system tests to test the defaults generator (accessing the database)
    @Test
    public void testUsersAndTrailsInDB() throws Exception {
        // call the /test path, which generates users and trails in the database
        this.restTemplate.getForObject("http://localhost:" + port + "/test", String.class);

        // check that trails page contains the generated trail
        assert this.restTemplate.getForObject("http://localhost:" + port + "/trails", String.class)
                .contains("Cardiff Castle to Queen Street");

        // check that the leaderboard page contains the generated useres
        String[] users = {"alice", "bob", "susan", "david", "emily", "ryan", "olivia", "michael", "emma"};

        for (String user : users) {
            assert this.restTemplate.getForObject("http://localhost:" + port + "/leaderboard", String.class)
                    .contains(user);
        }
    }

}
