/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author gdallegi
 */
import java.util.Date;

public class RendezVous {
    private int id;
    private int id_livraison;
    private Date date_livraison;
   
    // Constructeur
    public RendezVous(int id, int id_livraison, Date date_livraison) {
        this.id = id;
        this.id_livraison = id_livraison;
        this.date_livraison = date_livraison;
    }
   
    // Méthodes d'accès (getters) et de modification (setters)
    public int getId() {
        return id;
    }
   
    public void setId(int id) {
        this.id = id;
    }
   
    public int getIdLivraison() {
        return id_livraison;
    }
   
    public void setIdLivraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }
   
    public Date getDateLivraison() {
        return date_livraison;
    }
   
    public void setDateLivraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }
   
    // Méthode toString()
    @Override
    public String toString() {
        return "RendezVous{" +
                "id=" + id +
                ", id_livraison=" + id_livraison +
                ", date_livraison=" + date_livraison +
                '}';
    }
}