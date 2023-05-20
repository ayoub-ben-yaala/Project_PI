/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usernvme
 */
public class Panier {

    int id;
    List<ligne_commande> meds;

    public Panier(int id) {
        this.id = id;
        
    }

    public Panier() {
        this.meds = new ArrayList<>();
    }

    public Panier(int aInt, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ligne_commande> getMeds() {
        return meds;
    }

    public void setMeds(List<ligne_commande> meds) {
        this.meds = meds;
    }

    public void ajouterMed(ligne_commande L) {
        meds.add(L);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", meds=" + meds + '}';
    }

   
    
}
