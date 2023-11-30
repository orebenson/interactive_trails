package com.Team4.SmartTowns.profile.controllers;


import com.Team4.SmartTowns.profile.service.ProfileService;
import com.Team4.SmartTowns.trails.model.Trail;
import com.Team4.SmartTowns.trails.service.TrailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

//    private ProfileService profileService;
//    private TrailService trailService;
//
//    public ProfileController(ProfileService profileService, TrailService trailService){
//        this.profileService = profileService;
//        this.trailService = trailService;
//    }

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


//    @GetMapping("/profile")
//    public ModelAndView getProfile(Principal principal) {
//        ModelAndView mav = new ModelAndView("profile/profilePage");
//        String loggedInUser = principal.getName();
//        List<Trail> startedTrails = trailService.getStartedTrailsByUsername(loggedInUser);
//        for(Trail trail : startedTrails){
//            System.out.println(trail.getName());
//        }
//        return mav;
//    }


}
