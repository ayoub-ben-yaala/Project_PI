/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pi.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pi.entities.Commande;
import pi.entities.Panier;
import pi.services.service_commande;
import pi.services.service_panier;

/**
 *
 * @author usernvme
 */
public class PIJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = df.parse("12-10-2011"); 
        Commande Commande = new Commande(2,"en cours",date,1,1500);
        Commande Commande1 = new Commande(3,"en courssssss",date,1,1500);
        service_commande ServCom= new service_commande();
        service_panier panierService = new service_panier();
        Panier panier = new Panier();
        
       
       panierService.modifier(panier);
        
        /*
       ServCom.ajouter(Commande);*/
       ServCom.modifier(Commande1);
       //ServCom.supprimer(Commande);
       ServCom.afficher();
       
    }
    
}
