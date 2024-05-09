package com.Team4.SmartTowns.integrationTests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class AdminSecurityMockTests {
    @Autowired
    private MockMvc mockMvc;

    // integration - full container mockmvc with security check - add trails only accessible by admin users
    @Test
    public void testAddTrailPageNotAdmin() throws Exception {
        this.mockMvc.perform(get("/admin/trails/add")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden()); // user is forbidden due to not being an admin
    }

    @Test
    public void testAddTrailPageAsAdmin() throws Exception {
        this.mockMvc.perform(get("/admin/trails/add")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Add"))); // user is allowed due to being an admin
    }
}
