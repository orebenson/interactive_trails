package com.Team4.SmartTowns.registrationform.model;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RegistrationForm {
    private String userName;
    private String email;
    private String address;
    private String password;
    private String address2;
    private String city;
    private String zipCode;

//    public RegistrationForm(String userName, String email, String address, String address2, String city, String zipCode) {
//        this.userName = userName;
//        this.email = email;
//        this.address = address;
//        this.address2 = address2;
//        this.city = city;
//        this.zipCode = zipCode;
//    }
    public  RegistrationForm(){
        this.userName = "";
        this.email = "";
        this.address = "";
        this.address2 = "";
        this.city = "";
        this.zipCode = "";
    }
}
