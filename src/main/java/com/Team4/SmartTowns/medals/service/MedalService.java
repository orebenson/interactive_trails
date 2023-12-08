package com.Team4.SmartTowns.medals.service;

import org.springframework.stereotype.Service;

@Service
public interface MedalService {

//    List<Medal> getMedalsForUser(String username);

    void awardMedalToUser(String medalName);



}