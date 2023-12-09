package com.Team4.SmartTowns.medals.model;

import java.util.List;


public interface MedalRepository {

    void saveMedalToUser(String username, String medalName);

//    List<Medal> findMedalsForUsers(String username);

}
