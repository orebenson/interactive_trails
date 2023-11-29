package com.Team4.SmartTowns.registrationform.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationFormController {


    @GetMapping(value = {"/registration"})
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("/registration/registrationForm");
        modelAndView.addObject("registrationForm", new Profile());
         return modelAndView;
    }

    @GetMapping("/registration/newregister")
    @ResponseBody
    public String postResponseController() {
        return ";";
    }


    @PostMapping("/registration/newregister")
    public ModelAndView postAddTrail(@ModelAttribute("registrationForm") Profile registrationForm){

//        Long id = trailService.createTrail(trail);
//        System.out.println(trailService.getTrailById(id));
        System.out.println(registrationForm.getUserName());
        ModelAndView mav = new ModelAndView("redirect:/");

        return mav;
    }



}
