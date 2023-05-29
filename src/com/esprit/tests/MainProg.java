/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Medecin;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOrdonnancee;
import com.esprit.services.ServiceMedecin;
import com.esprit.utils.DataSource;
import java.time.LocalDate;
import java.sql.Date;


/**
 *
 * 
 */
public class MainProg {
    
    public static void main(String[] args) {

       ServiceOrdonnancee so = new ServiceOrdonnancee();
       so.ajouter(new Ordonnance( 1,"frsfsf", "fdfdsf", Date.valueOf(LocalDate.now()),"Non Traité"));
      // ServiceMedecin x=new ServiceMedecin();
     //  x.ajouter(new Medecin("000","....","test"));
        
    }
}
