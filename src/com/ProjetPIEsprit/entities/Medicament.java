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
    private Categorie cat;
    private Type ty;
    
     public Medicament(String nom_medi,int reference,String notice, int prix, String DateExp, Categorie cat, Type ty) {
         this.cat = cat;
         this.ty = ty;
         this.nom_medi = nom_medi;
        this.reference = reference;
        this.notice = notice;
        this.prix = prix;
        this.DateExp=DateExp;
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

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Type getTy() {
        return ty;
    }

    public void setTy(Type ty) {
        this.ty = ty;
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
