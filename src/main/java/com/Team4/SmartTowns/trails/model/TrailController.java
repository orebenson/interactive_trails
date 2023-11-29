package com.Team4.SmartTowns.trails.model;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import com.Team4.SmartTowns.checkpoints.service.CheckpointService;
import com.Team4.SmartTowns.trails.service.TrailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrailController {

    private TrailService trailService;
    private CheckpointService checkpointService;

    public TrailController(TrailService trailService, CheckpointService checkpointService){

        this.trailService = trailService;
        this.checkpointService = checkpointService;

        // Create some default trails
        Trail trail1 = createTempTrail("Cardiff Castle to Queen Street", "Cardiff", "A serene town along the shore, blending cobblestone streets with beachside cafes and historic cottages.");
        Trail trail2 = createTempTrail("Newport Castle to the Transporter Bridge", "Newport","Newport, a historic town, showcases colonial charm with cobbled streets and well-preserved architecture. Situated along the river, it reflects a blend of heritage and modernity, making it a captivating destination in Wales.");
        Trail trail3 = createTempTrail("Swansea Mumbles to Gower", "Swansea","Nestled along the scenic coastline, Swansea is a coastal haven known for its quaint beauty. With sandy beaches, a bustling marina, and a rich maritime history, the town offers a serene escape by the sea.");

        createTempCheckpoint(trail1,0, "Cardiff Castle", "A historic fortress in Cardiff, Wales, blending Roman, medieval, and Victorian architecture in the heart of the city.");
        createTempCheckpoint(trail1,1, "checkpoint 2", "(Description of checkpoint 2)");
        createTempCheckpoint(trail2,0, "Newport Castle", "(Description of checkpoint 1)");
        createTempCheckpoint(trail3,0, "Mumbles", "(Description of checkpoint 1)");

        trailService.createTrail(trail1);
        trailService.createTrail(trail2);
        trailService.createTrail(trail3);

    }

    private Trail createTempTrail(String name, String location, String description) {
        // Method for helping create default trails
        Trail tempTrail = new Trail();
        tempTrail.setName(name);
        tempTrail.setLocation(location);
        tempTrail.setDescription(description);
        return tempTrail;
    }

    private Trail createTempCheckpoint(Trail trail, int pos, String name, String description) {
        // Method to help create default trails checkpoint
        List<Checkpoint> checkpoints = trail.getCheckpoints();
        Checkpoint temp = checkpoints.get(pos);
        temp.setName(name);
        temp.setDescription(description);
        trail.setCheckpoints(checkpoints);
        return trail;
    }

    @GetMapping("/trails")
    public ModelAndView showTrails() {
        ModelAndView mvc = new ModelAndView("/trails/trails");
        List<Trail> trails = trailService.getAllTrails();
        mvc.addObject("trails", trails);

        return mvc;
    }

    @GetMapping("/trails/{id}")
    public ModelAndView getDetails(@PathVariable Long id){
        ModelAndView mvc = new ModelAndView("/trails/trailDetails");
        Trail trail = trailService.getTrailById(id);
//        List<Checkpoint> checkpoints = checkpointService.getAllCheckpoints();
//        mvc.addObject("checkpoints",checkpoints);
        mvc.addObject("trail", trail);
        return mvc;
    }


}
