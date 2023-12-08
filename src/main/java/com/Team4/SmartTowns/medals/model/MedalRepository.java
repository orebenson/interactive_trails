package com.Team4.SmartTowns.medals.model;

import java.util.List;


public interface MedalRepository {


    void saveMedalToUser(String medalName, String username);

    //award medals to users method

//    List<Medal> findMedalsForUsers(String username);

}
