/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.entities;

/**
 *
 * @author eya trabelsi
 */
public class Medicament {
    private int idMed;
    private String nom_medi;
    private int reference;
    private String notice;
    private int prix;
    private String DateExp;
    private String categorie;
    private String type;
    private String nomcat;
    private String nomtyp;
    
     public Medicament(String nomcat, String nomtyp, String nom_medi, int reference, String DateExp, int prix, String notice) {
         this.nomcat = nomcat;
         this.nomtyp = nomtyp;
         this.nom_medi = nom_medi;
        this.reference = reference;
        this.DateExp=DateExp;
        this.prix = prix;
        this.notice = notice;
             
    }

    public Medicament(int idMed, String nom_medi, int reference, String notice, int prix, String DateExp, String categorie, String type) {
        this.idMed = idMed;
        this.nom_medi = nom_medi;
        this.reference = reference;
        this.notice = notice;
        this.prix = prix;
        this.DateExp = DateExp;
        this.categorie = categorie;
        this.type = type;
    }

    public Medicament() {
    }

  

    public Medicament(String nom_medi, String nomcat, String nomtyp) {
        this.nom_medi = nom_medi;
        this.nomcat = nomcat;
        this.nomtyp = nomtyp;
    }

    
    public Medicament(String nom_medi, int reference, String notice, int prix, String DateExp) {
        this.nom_medi = nom_medi;
        this.reference = reference;
        this.notice = notice;
        this.prix = prix;
        this.DateExp = DateExp;
    }

    @Override
    public String toString() {
        return "Medicament{" + "nom_medi=" + nom_medi + ", reference=" + reference + ", notice=" + notice + ", prix=" + prix + ", DateExp=" + DateExp + '}';
    }

    public int getIdMed() {
        return idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public String getNom_medi() {
        return nom_medi;
    }

    public void setNom_medi(String nom_medi) {
        this.nom_medi = nom_medi;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDateExp() {
        return DateExp;
    }

    public void setDateExp(String DateExp) {
        this.DateExp = DateExp;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
   
}
