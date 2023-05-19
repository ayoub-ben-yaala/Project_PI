/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

/**
 *
 * @Ayoub Ben Yaala
 */
public class SousPharmacie extends SuperPharmacie {
    
    private String Domaine;

    public SousPharmacie(String Domaine, String MatriculeFiscale, String UserName, String Email, String Password, String Phone, String Adress, String Role) {
        super(MatriculeFiscale, UserName, Email, Password, Phone, Adress, Role);
        this.Domaine = Domaine;
    }

    public SousPharmacie(String Domaine, String MatriculeFiscale, int idUser, String UserName, String Email, String Password, String Phone, String Adress, String Role) {
        super(MatriculeFiscale, idUser, UserName, Email, Password, Phone, Adress, Role);
        this.Domaine = Domaine;
    }

 
    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String Domaine) {
        this.Domaine = Domaine;
    }

    @Override
    public String toString() {
        return "SousPharmacie{" + "Domaine=" + Domaine + '}';
    }

 
    
    
    
}
