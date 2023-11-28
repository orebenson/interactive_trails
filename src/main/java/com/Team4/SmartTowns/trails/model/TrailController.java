package com.Team4.SmartTowns.trails.model;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import com.Team4.SmartTowns.checkpoints.service.CheckpointService;
import com.Team4.SmartTowns.trails.service.TrailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrailController {

    private TrailService trailService;

    public TrailController(TrailService trailService){
        this.trailService = trailService;
    }

    private CheckpointService checkpointService;

    public void CheckpointService(CheckpointService checkpointService) {
    this.checkpointService = checkpointService;
    }

    @GetMapping("/trails")
    public ModelAndView showTrails() {
        ModelAndView mvc = new ModelAndView("/trails/trails");
        List<Trail> trails = trailService.getAllTrails();
        mvc.addObject("trails", trails);
        return mvc;

        //adding some default data to populate the page.
//        mvc.addObject("id", "Trail ID: 1");
//        mvc.addObject("name", "Trail Name: Cardiff Castle to Queen Street");
//        mvc.addObject("location", "Location: Cardiff");
//        mvc.addObject("description", "Description: Cardiff Castle to Queen Street description");


    }

    @GetMapping("/trails/{id}")
    public ModelAndView getDetails(@PathVariable Long id){
        ModelAndView mvc = new ModelAndView("/trails/trailDetails");
        Trail trails = trailService.getTrailById(id);
//        List<Checkpoint> checkpoints = checkpointService.getAllCheckpoints();
//        mvc.addObject("checkpoints",checkpoints);
        mvc.addObject("trail", trails);
        return mvc;
    }


}
