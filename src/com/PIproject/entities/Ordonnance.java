
package com.PIproject.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Ordonnance {
    private int id ;
    private int Reference;
    private int id_Medecin ;
    private int id_Medicament ;
    private Date date ;
    private String Statut ; 

    public Ordonnance(int id, int Reference, int id_Medecin, int id_Medicament, Date date, String Statut) {
        this.id = id;
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Medicament = id_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

      public Ordonnance( int Reference, int id_Medecin, int id_Medicament, Date date, String Statut) {
      
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Medicament = id_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

    public Ordonnance(int reference, int id_Medecin, int Nom_Medicament, LocalDate date, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Ordonnance(int id_Medecin, String nomMedicament, Date date, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }

    public int getId_Medecin() {
        return id_Medecin;
    }

    public void setNom_Medecin(int id_Medecin) {
        this.id_Medecin = id_Medecin;
    }

    public int getId_Medicament() {
        return id_Medicament;
    }

    public void setNom_Medicament(int id_Medicament) {
        this.id_Medicament = id_Medicament;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }    

    public Date getDateOrdonnance() {
        return this.date;
       }

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", Reference=" + Reference + ", id_Medecin=" + id_Medecin + ", id_Medicament=" + id_Medicament + ", date=" + date + ", Statut=" + Statut + '}';
    }
}