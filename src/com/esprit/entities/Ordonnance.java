/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author 
 */
public class Ordonnance {
    private int id ;
    private int Reference;
    private String Nom_Medecin ;
    private String Nom_Medicament ;
    private Date date ;
    private String Statut ; 

    public Ordonnance(int id, int Reference, String nom_Medecin, String Nom_Medicament, Date date, String Statut) {
        this.id = id;
        this.Reference = Reference;
        this.Nom_Medecin = Nom_Medecin;
        this.Nom_Medicament = Nom_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

      public Ordonnance( int Reference, String Nom_Medecin, String Nom_Medicament, Date date, String Statut) {
      
        this.Reference = Reference;
        this.Nom_Medecin = Nom_Medecin;
        this.Nom_Medicament = Nom_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

    public Ordonnance(int reference, String Nom_Medecin, String Nom_Medicament, LocalDate date, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Ordonnance(String nomMedecin, String nomMedicament, Date date, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }

    public String getNom_Medecin() {
        return Nom_Medecin;
    }

    public void setNom_Medecin(String Nom_Medecin) {
        this.Nom_Medecin = Nom_Medecin;
    }

    public String getNom_Medicament() {
        return Nom_Medicament;
    }

    public void setNom_Medicament(String Nom_Medicament) {
        this.Nom_Medicament = Nom_Medicament;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }    

    public Date getDateOrdonnance() {
        return this.date;
       }
}
