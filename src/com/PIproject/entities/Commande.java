/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author usernvme
 */
public class Commande {
    int id_commande;
    String etat;
    LocalDate date;
    public SousPharmacie pharmacie;
    

    public Commande(int id_commande, String etat, LocalDate date, SousPharmacie pharmacie, float prix_tot) {
        this.id_commande = id_commande;
        this.etat = etat;
        this.date = date;
        this.pharmacie = pharmacie;
        
    }

    public Commande(String etat, LocalDate date, SousPharmacie phar) {
        this.etat = etat;
        this.date = date;
        this.pharmacie = phar;
        
    }

    public Commande(int id_commande, String etat, LocalDate date) {
        this.id_commande = id_commande;
        this.etat = etat;
        this.date = date;
        
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SousPharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(SousPharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

   

    @Override
    public String toString() {
        return "commande{" + "id_commande=" + id_commande + ", etat=" + etat + ", date=" + date + ", phar=" + pharmacie +  '}';
    }

    public Commande() {
    }
           
    
    
}
