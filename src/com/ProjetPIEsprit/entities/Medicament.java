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

    public Medicament(String nom_medi, String nomcat, String nomtyp) {
        this.nom_medi = nom_medi;
        this.nomcat = nomcat;
        this.nomtyp = nomtyp;
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

  

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public String getNomtyp() {
        return nomtyp;
    }

    public void setNomtyp(String nomtyp) {
        this.nomtyp = nomtyp;
    }


    public Medicament(String nom_medi, int reference, String notice, int prix, String DateExp) {
        this.nom_medi = nom_medi;
        this.reference = reference;
        this.notice = notice;
        this.prix = prix;
        this.DateExp = DateExp;
    }

    public String getDateExp() {
        return DateExp;
    }

    public void setDateExp(String DateExp) {
        this.DateExp = DateExp;
    }

     
       public String getnom_medi() {
        return nom_medi;
    }

    public void setnom_medi(String
            nom_medi) {
        this.nom_medi = nom_medi;
    }
    
      public int getreference() {
        return reference;
    }

    public void setreference(int reference) {
        this.reference = reference;
    }
    
     public String getnotice() {
        return notice;
    }

    public void setnotice(String notice) {
        this.notice = notice;
    }
    
     public int getprix() {
        return prix;
    }

    public void setprix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Medicament{" + "nom_medi=" + nom_medi + ", reference=" + reference + ", notice=" + notice + ", prix=" + prix + ", DateExp=" + DateExp + '}';
    }
    
   
}
