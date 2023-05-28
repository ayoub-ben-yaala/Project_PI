/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetPIEsprit.services;
import com.ProjetPIEsprit.entities.Type;
import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.entities.Medicament;
import java.util.List;

/**
 *
 * @author eya trabelsi
 */
public interface IService<T>  {
     public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(T p);
    public List<T> afficher();
}
