package com.Team4.SmartTowns.medals.service;

import com.Team4.SmartTowns.checkpoints.model.Checkpoint;
import com.Team4.SmartTowns.checkpoints.model.CheckpointRepository;
import com.Team4.SmartTowns.checkpoints.service.CheckpointService;
import com.Team4.SmartTowns.medals.model.MedalRepository;
import com.Team4.SmartTowns.medals.model.Medal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedalServiceImpl implements MedalService {

    private MedalRepository medalRepository;
    private CheckpointService checkpointService;

    public MedalServiceImpl(CheckpointService checkpointService, MedalRepository medalRepository){
        this.checkpointService = checkpointService;
        this.medalRepository = medalRepository;
    }




//    @Override
//    public List<Medal> getMedalsForUser(String username) {
//        return medalRepository.findMedalsForUsers(username);
//    }

    @Override
    public void awardMedalToUser(String username, String medalName) {
        List<Checkpoint> checkpointsReached = checkpointService.getCheckpointsByUsername(username);
        //look into list.size for array for above

        String medal = determineMedal(checkpointsReached);
        //if medal != "none" then insert medal

        medalRepository.saveMedal(String medalName, String username));

    }

    private String determineMedal(int checkpointsSum) {
        if (checkpointsSum >= 60) {
            return "GOLD";
        } else if (checkpointsSum >= 40) {
            return "SILVER";
        } else if (checkpointsSum >=20) {
            return "BRONZE";
        } else {
            return "none";
        }
    }


}
