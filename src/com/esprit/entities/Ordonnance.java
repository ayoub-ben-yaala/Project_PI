/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author 
 */
public class Ordonnance {
    private int id ;
    private int Reference;
    private int id_Medecin ;
    private int id_Médicament ;
    private Date date ;
    private String Statut ; 

    public Ordonnance(int id, int Reference, int id_Medecin, int id_Médicament, Date date, String Statut) {
        this.id = id;
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Médicament = id_Médicament;
        this.date = date;
        this.Statut = Statut;
    }

      public Ordonnance( int Reference, int id_Medecin, int id_Médicament, Date date, String Statut) {
      
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Médicament = id_Médicament;
        this.date = date;
        this.Statut = Statut;
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

    public int getid_Medecin() {
        return id_Medecin;
    }

    public void setNom_Medecin(int id_Medecin) {
        this.id_Medecin = id_Medecin;
    }

    public int getId_Médicament() {
        return id_Médicament;
    }

    public void setId_Médicament(int id_Médicament) {
        this.id_Médicament = id_Médicament;
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

    public int getId_Medicament() {
        return this.id_Médicament;
      }

    public Date getDateOrdonnance() {
        return this.date;
       }
}
