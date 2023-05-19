/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.main;

/**
 *
 * @Ayoub Ben Yaala
 */


import com.PIproject.entities.User;
import com.PIproject.services.ServiceUser;
import com.PIproject.utils.DataSource;

public class MainProg {
    
    public static void main(String[] args) {
        ServiceUser Suser = new ServiceUser();
        //Suser.ajouter(new User("Ayoub", "ayoubbenyaala@gmail.com","azerty","26409606","Tunis Mhamdia","SuperPharmacie"));
        //Suser.supprimer(new User(1,"","","","","",""));
        //Suser.modifier(new User(7,"Ayoub","ayoubbenyaala@gmail.com","open","26409606","Ben Arous Tunis","SuperPharmacie"));
        System.out.println(Suser.afficher());
    }
}
