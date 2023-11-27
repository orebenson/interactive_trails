package com.Team4.SmartTowns;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class profilePage {
    @GetMapping(value = {"/profile"})
    public ModelAndView userName(ModelAndView modelAndView){
       modelAndView.setViewName("profilePage.html");
       modelAndView.addObject("name", "John");
        modelAndView.addObject("town", "Cardiff Town");
        modelAndView.addObject("numberOfTrails", 2);
        modelAndView.addObject("about", "I love trails\n" +
                "\n" +
                "Embarking on a Journey\n" +
                "\n" +
                "Cool Trail!")
    return modelAndView;
    }

//    public ModelAndView showProfilePage(){
//
//        ModelAndView modelAndView = new ModelAndView("/profiles/{profileID}");
//        return modelAndView;
//    }
}
