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
    private int CIN;

    public Livreur(int CIN, String UserName, String Email, String Password, String Phone, String Adress) {
        super(UserName, Email, Password, Phone, Adress);
        this.CIN = CIN;
    }

    public Livreur(int CIN, int idUser, String UserName, String Email, String Password, String Phone, String Adress) {
        super(idUser, UserName, Email, Password, Phone, Adress);
        this.CIN = CIN;
    }

 

    public int getCIN() {
        return CIN;
    }

    public void setNumCNSS(int CIN) {
        this.CIN = CIN;
    }

    @Override
    public String toString() {
        return "Livreur{" + "NumCNSS=" + CIN + '}';
    }
    
    
}
