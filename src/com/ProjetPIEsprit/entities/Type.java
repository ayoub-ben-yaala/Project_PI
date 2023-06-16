/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.entities;

/**
 *
 * @author eya trabelsi
 */
public class Type {
    
    private int id_type;
    private String nomtype;

    public Type(int id_type) {
        this.id_type = id_type;
    }

    
    
    
     
   public Type(String nomtype) {
        this.nomtype = nomtype;
    } 
   
    public Type(int id_type,String nomtype) {
        this.id_type = id_type;
        this.nomtype = nomtype;
    } 
    
    public int getid_type() {
        return id_type;
    }

    public void setid_type(int id_type) {
        this.id_type = id_type;
    }
    
    public String getNomtype() {
        return nomtype;
    }

    public void setNomtype(String nomtype) {
        this.nomtype = nomtype;
    }
    
    @Override
    public String toString() {
        return "Type{" + "id_type=" + id_type + ", nomtype=" + nomtype + '}';
    }
}
