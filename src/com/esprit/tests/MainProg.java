/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Livraison;
import com.esprit.entities.RendezVous;
import com.esprit.services.ServiceLivraison;
import com.esprit.services.ServiceRendezVous;
import com.esprit.utils.DataSource;
import com.mysql.cj.xdevapi.Type;
import java.sql.Date;

/**
 *
 * @author gdallegi
 */
public class MainProg {
    
    public static void main(String[] args) {
//        ServicePersonne sp = new ServicePersonne();
//        System.out.println(sp.afficher());
        ServiceLivraison sL = new ServiceLivraison();
        sL.ajouter(new Livraison(5,"50", 1,1));
        ServiceRendezVous sR = new ServiceRendezVous();
      
    }
}
