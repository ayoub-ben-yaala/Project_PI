/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

/**
 *
 * @author eya trabelsi
 */
public class Categorie {
    
        private int id_categorie;
    private String nomcategorie;

    public Categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    
    
    
     
   public Categorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    } 
   
    public Categorie(int id_categorie,String nomcategorie) {
        this.id_categorie = id_categorie;
        this.nomcategorie = nomcategorie;
    } 
    
    public int getid_categorie() {
        return id_categorie;
    }

    public void setid_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    
    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    
    @Override
    public String toString() {
        return "categorie{" + "id_categorie=" + id_categorie + ", nomcategorie=" + nomcategorie + '}';
    }
}