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
    private int cin;

    public Livreur(int cin, String UserName, String Email, String Password, String Phone, String Adress) {
        super(UserName, Email, Password, Phone, Adress);
        this.cin = cin;
    }

    public Livreur(int cin, int idUser, String UserName, String Email, String Password, String Phone, String Adress) {
        super(idUser, UserName, Email, Password, Phone, Adress);
        this.cin = cin;
    }

    public Livreur(int cin,String UserName, String Email, String Phone, String Adress) {
        super(UserName, Email, Phone, Adress);
        this.cin =cin;
    }

 

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Livreur{" + "NumCNSS=" + cin + '}';
    }
    
    
}
