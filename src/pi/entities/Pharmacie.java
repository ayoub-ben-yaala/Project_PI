/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

/**
 *
 * @author usernvme
 */
public class Pharmacie {
    
    int id;
    String nom;

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

    public Pharmacie(int id) {
        this.id = id;
    }

    public Pharmacie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  nom ;
    }
    
    
    
}
