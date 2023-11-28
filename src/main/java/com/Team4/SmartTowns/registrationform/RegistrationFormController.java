package com.Team4.SmartTowns.registrationform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationFormController {
    @GetMapping(value = {"/registration"})
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("/registration/registrationForm");
        modelAndView.addObject("newuserPrompt", "enter your username");
        modelAndView.addObject("passwordPrompt", "enter your password");
     return modelAndView;
    }
}
