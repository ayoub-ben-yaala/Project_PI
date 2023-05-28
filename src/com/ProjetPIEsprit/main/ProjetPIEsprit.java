/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.main;

import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.entities.Medicament;
import com.ProjetPIEsprit.entities.Type;
import com.ProjetPIEsprit.services.ServiceCategorie;
import com.ProjetPIEsprit.services.ServiceType;
import com.ProjetPIEsprit.services.ServiceMedicament;
import com.ProjetPIEsprit.utils.DataSource;

/**
 *
 * @author eya trabelsi
 */
public class ProjetPIEsprit {
    
    public static void main(String[] args) {
       // ServiceCategorie sc = new ServiceCategorie();
       // System.out.println(sc.afficher());
        ServiceCategorie sc1 = new ServiceCategorie();
        Categorie c = new Categorie("azerty");
        //sc1.ajouter(new Categorie("Esthetique"));
        //System.out.println(sc1.afficher());
       sc1.modifier(c);
       //ServiceType st1 = new ServiceType();
       //st1.ajouter(new Type("Sachet"));
       // ServiceType st = new ServiceType();
       // System.out.println(st.afficher());
       
      // ServiceMedicament m1 = new ServiceMedicament();
      // Categorie cat = new Categorie(2);
      // Type ty = new Type(1);
     //  m1.ajouter(new Medicament("eya",1002
     //          ,"alergie",100,"13-10-1998",cat,ty));
     //   System.out.println( m1.afficher());
    }
}
