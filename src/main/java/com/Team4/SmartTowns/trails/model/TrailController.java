package com.Team4.SmartTowns.trails.model;

import com.Team4.SmartTowns.trails.service.TrailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrailController {

    private TrailService trailService;

    public TrailController(TrailService trailService){
        this.trailService = trailService;
    }

    @GetMapping("/trails")
    public ModelAndView showTrails() {
        ModelAndView mvc = new ModelAndView("/trails/trails");
        List<Trail> trails = trailService.getAllTrails();

        mvc.addObject("trails", trails);

        //adding some default data to populate the page.

//        mvc.addObject("id", "Trail ID: 1");
//        mvc.addObject("name", "Trail Name: Cardiff Castle to Queen Street");
//        mvc.addObject("location", "Location: Cardiff");
//        mvc.addObject("description", "Description: Cardiff Castle to Queen Street description");

        return mvc;
    }

//    @GetMapping("/trails/{id}")
//    public ModelAndView showDetails(){
//        ModelAndView mvc = new ModelAndView("/trails/trailsId");
//        return mvc;
//    }


}
