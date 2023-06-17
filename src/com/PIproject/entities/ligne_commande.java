package com.PIproject.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usernvme
 */
public class ligne_commande {
    int id;
    int id_med;
    int quantite;

    public ligne_commande(int id, int quantite, int id_med) {
        this.id = id;
        this.quantite = quantite;
        this.id_med = id_med;
    }

    public ligne_commande(int id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getquantite() {
        return quantite;
    }

    public void setquantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }
    
}
