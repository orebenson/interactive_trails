package com.Team4.SmartTowns.medals;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Medals {
    private Long medalId;
    private String medalName;
    private String description;


    public Medals() {

        this.medalId = null;
        this.medalName = "";
        this.description = "";
    }
}
