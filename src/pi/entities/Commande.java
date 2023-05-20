/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

import java.util.Date;

/**
 *
 * @author usernvme
 */
public class Commande {
    int id_commande;
    String etat;
    Date date;
    int id_phar;
    

    public Commande(int id_commande, String etat, Date date, int id_phar, float prix_tot) {
        this.id_commande = id_commande;
        this.etat = etat;
        this.date = date;
        this.id_phar = id_phar;
        
    }

    public Commande(String etat, Date date, int id_phar) {
        this.etat = etat;
        this.date = date;
        this.id_phar = id_phar;
        
    }

    public Commande(int id_commande, String etat, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_phar() {
        return id_phar;
    }

    public void setId_phar(int id_phar) {
        this.id_phar = id_phar;
    }

   

    @Override
    public String toString() {
        return "commande{" + "id_commande=" + id_commande + ", etat=" + etat + ", date=" + date + ", id_phar=" + id_phar +  '}';
    }
           
    
    
}
