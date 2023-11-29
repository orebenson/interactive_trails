package com.Team4.SmartTowns.profile;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @GetMapping(value = {"/profile"})
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView("/profile/profilePage");
//       modelAndView.setViewName("profilePage.html");

       //dummy data for the profile page fields.
       modelAndView.addObject("name", "John");
        modelAndView.addObject("town", "Cardiff Town");
        modelAndView.addObject("unlockedTrails", 2);
        modelAndView.addObject("completedTrails", 4);

        modelAndView.addObject("lockedTrails", 2);
        modelAndView.addObject("about", "Loving the new Trail application!");
    return modelAndView;
    }

//
//    @GetMapping("/profile") {
//        public ModelAndView getProfile(Principal principal) {
//            String loggedInUser = principal.getName()
//        }
//    }

}
