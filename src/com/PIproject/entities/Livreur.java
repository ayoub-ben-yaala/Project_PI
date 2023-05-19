/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

/**
 *
 * @Ayoub Ben Yaala
 */
public class Livreur extends User {
    private String NumCNSS;

    public Livreur(String NumCNSS, String UserName, String Email, String Password, String Phone, String Adress, String Role) {
        super(UserName, Email, Password, Phone, Adress, Role);
        this.NumCNSS = NumCNSS;
    }

    public Livreur(String NumCNSS, int idUser, String UserName, String Email, String Password, String Phone, String Adress, String Role) {
        super(idUser, UserName, Email, Password, Phone, Adress, Role);
        this.NumCNSS = NumCNSS;
    }

 

    public String getNumCNSS() {
        return NumCNSS;
    }

    public void setNumCNSS(String NumCNSS) {
        this.NumCNSS = NumCNSS;
    }

    @Override
    public String toString() {
        return "Livreur{" + "NumCNSS=" + NumCNSS + '}';
    }
    
    
}
