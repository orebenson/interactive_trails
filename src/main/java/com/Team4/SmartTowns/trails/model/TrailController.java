package com.Team4.SmartTowns.trails.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrailController {

    @GetMapping("/trails")
    public ModelAndView showTrails() {
        ModelAndView mvc = new ModelAndView("/trails/trails");
        return mvc;

    }
}
