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
    
    private String NomPharmacie;

    public SousPharmacie(int idUser) {
        super(idUser);
    }
    
    

    public SousPharmacie(String NomPharmacie, String MatriculeFiscale, String UserName, String Email, String Password, int Phone, String Adress) {
        super(MatriculeFiscale, UserName, Email, Password, Phone, Adress);
        this.NomPharmacie = NomPharmacie;
    }
       public SousPharmacie(String NomPharmacie, String MatriculeFiscale, String UserName, String Email, String Password, int Phone, String Adress,String Statut) {
        super(MatriculeFiscale, UserName, Email, Password, Phone, Adress,Statut);
        this.NomPharmacie = NomPharmacie;
    }

    public SousPharmacie(String NomPharmacie, String MatriculeFiscale, int idUser, String UserName, String Email, String Password, int Phone, String Adress) {
        super(MatriculeFiscale, idUser, UserName, Email, Password, Phone, Adress);
        this.NomPharmacie = NomPharmacie;
    }
     public SousPharmacie(String NomPharmacie, String MatriculeFiscale, int idUser, String UserName, String Email, String Password, int Phone, String Adress,String Statut) {
        super(MatriculeFiscale, idUser, UserName, Email, Password, Phone, Adress,Statut);
        this.NomPharmacie = NomPharmacie;
    }

    public SousPharmacie(String NomPharmacie, String MatriculeFiscale, int idUser, String UserName, String Email, int Phone, String Adress, String Statut) {
        super(MatriculeFiscale, idUser, UserName, Email, Phone, Adress, Statut);
        this.NomPharmacie = NomPharmacie;
    }

 
    public String getNomPharmacie() {
        return NomPharmacie;
    }

    public void setNomPharmacie(String NomPharmacie) {
        this.NomPharmacie = NomPharmacie;
    }

    @Override
    public String toString() {
        return "SousPharmacie{" + "Domaine=" + NomPharmacie + '}';
    }

 
    
    
    
}
