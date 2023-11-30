package com.Team4.SmartTowns.profile.controllers;


import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import com.Team4.SmartTowns.profile.model.Profile;
import com.Team4.SmartTowns.profile.service.ProfileService;
import com.Team4.SmartTowns.trails.model.Trail;
import com.Team4.SmartTowns.trails.service.TrailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProfileController {

    private ProfileService profileService;
    private TrailService trailService;

    public ProfileController(ProfileService profileService, TrailService trailService){
        this.profileService = profileService;
        this.trailService = trailService;
    }

    @GetMapping(value = {"/profile"})
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("/profile/profilePage");
        //dummy data for the profile page fields.
        modelAndView.addObject("name", "John");
        modelAndView.addObject("town", "Cardiff Town");
        modelAndView.addObject("unlockedTrails", 2);
        modelAndView.addObject("completedTrails", 4);
        modelAndView.addObject("lockedTrails", 2);
        modelAndView.addObject("about", "Loving the new Trail application!");
        return modelAndView;
    }


    @GetMapping("/profile-test")
    public ModelAndView getProfile(Principal principal) {
        ModelAndView mav = new ModelAndView("profile/profile-test");
        String loggedInUser = principal.getName();

        List<Trail> startedTrails = trailService.getStartedTrailsByUsername(loggedInUser);
        Profile profile = profileService.getProfile(loggedInUser);
        profile.setPassword("");

        // Calculate progress for each trail
        Map<String, List<Checkpoint>> checkpointsByTrail = new HashMap<>();
        for (Trail trail : startedTrails) {
            List<Checkpoint> collectedCheckpoints = new ArrayList<>();
            for (Checkpoint checkpoint : trail.getCheckpoints()) {
                if (profile.getCheckpoints().contains(checkpoint)) {
                    collectedCheckpoints.add(checkpoint);
                }
            }
            checkpointsByTrail.put(trail.getName(), collectedCheckpoints);
        }

        mav.addObject("profile", profile);
        mav.addObject("startedTrails", startedTrails);
        mav.addObject("checkpointsByTrail", checkpointsByTrail);

        return mav;
    }



}
