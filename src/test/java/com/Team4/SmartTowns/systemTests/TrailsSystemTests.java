package com.Team4.SmartTowns.systemTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrailsSystemTests {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // system test that the trails page is present when visited
    @Test
    public void trailsPageTest() throws Exception {
        assert this.restTemplate.getForObject("http://localhost:" + port + "/trails", String.class)
                .contains("view all the available trails here");
    }
}
