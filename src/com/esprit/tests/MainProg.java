/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Médecin;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOrdonnance;
import com.esprit.services.ServiceMédecin;
import com.esprit.utils.DataSource;
import java.time.LocalDate;
import java.sql.Date;


/**
 *
 * 
 */
public class MainProg {
    
    public static void main(String[] args) {
//        ServicePersonne sp = new ServicePersonne();
//        System.out.println(sp.afficher());
        ServiceOrdonnance so = new ServiceOrdonnance();
        so.ajouter(new Ordonnance( 1,1, 1, Date.valueOf(LocalDate.now()),""));
    }
}
