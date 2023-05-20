/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

/**
 *
 * @Ayoub Ben Yaala
 */
public class SuperPharmacie extends User {
    
    private String MatriculeFiscale;

    public SuperPharmacie(int idUser) {
        super(idUser);
    }

    
    public SuperPharmacie(String MatriculeFiscale, String UserName, String Email, String Password, String Phone, String Adress) {
        super(UserName, Email, Password, Phone, Adress);
        this.MatriculeFiscale = MatriculeFiscale;
    }

    public SuperPharmacie(String MatriculeFiscale, int idUser, String UserName, String Email, String Password, String Phone, String Adress) {
        super(idUser, UserName, Email, Password, Phone, Adress);
        this.MatriculeFiscale = MatriculeFiscale;
    }

  

    public String getMatriculeFiscale() {
        return MatriculeFiscale;
    }

    public void setMatriculeFiscale(String MatriculeFiscale) {
        this.MatriculeFiscale = MatriculeFiscale;
    }

    @Override
    public String toString() {
        return "SuperPharmacie{" + "MatriculeFiscale=" + MatriculeFiscale + '}';
    }
    
    
}
