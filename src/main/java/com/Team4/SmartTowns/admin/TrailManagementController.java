package com.Team4.SmartTowns.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrailManagementController {
    @GetMapping("/trails/add")
    public ModelAndView addTrail(){
        ModelAndView mav = new ModelAndView("admin/createTrail");
        return mav;
    }
}
