package com.Team4.SmartTowns.admin;

import com.Team4.SmartTowns.trails.Trail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrailManagementController {
    @GetMapping("/trails/add")
    public ModelAndView addTrail(){
        ModelAndView mav = new ModelAndView("admin/createTrail");
        mav.addObject("trail", new Trail());
        return mav;
    }


    @PostMapping("/trails/add")
    public ModelAndView submitTrail(@ModelAttribute("trail") Trail trail){
        // use the trail service to add the trail
        // if no errors:
            // trailService.createTrail(trail)
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }


//
//    @PostMapping("/trails/{id}/delete")
}
