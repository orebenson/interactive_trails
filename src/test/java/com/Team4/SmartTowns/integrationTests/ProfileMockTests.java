package com.Team4.SmartTowns.integrationTests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.Team4.SmartTowns.profile.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileMockTests {
    @Autowired
    private MockMvc mockMvc;

    // - integration - security redirection test to test redirect if not logged in
    @Test
    public void testProfilePageRedirect() throws Exception {
        this.mockMvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    // - integration - test creating a user in the database with post, then accessing their profile page
    @Test
    public void testProfilePageUsingTestDBUser() throws Exception {

        Profile testProfile = new Profile();
        testProfile.setUserName("testUser1");
        testProfile.setPassword("testUser1Password");

        this.mockMvc.perform(post("/registration/newregister")
                        .with(csrf())
                    .param("userName", testProfile.getUserName())
                    .param("password", testProfile.getPassword())
                    .param("email", testProfile.getEmail())
                    .param("address", testProfile.getAddress())
                    .param("address2", testProfile.getAddress2())
                    .param("city", testProfile.getCity())
                    .param("zipCode", testProfile.getZipCode()))
            .andExpect(status().is3xxRedirection());


        this.mockMvc.perform(get("/profile")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser1").roles("USER").password("testUser1Password")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username: testUser1"))//The profile page should have their name at the top in this format
                );
    }

}
