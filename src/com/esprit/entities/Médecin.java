/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 *
 */
public class Médecin {
    
    private int id;
    private String nom;
    private String prenom;
    private String spécialité ; 
    public Médecin(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Médecin(int id, String nom, String prenom , String spécialité ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.spécialité = spécialité ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
      public String getSpécialité() {
        return spécialité;
    }

    public void setSpécialité(String spécialité) {
        this.spécialité = spécialité;
    }
    
    

    @Override
    public String toString() {
        return "Médecin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ",spécialité=" + spécialité +'}';
    }
}
