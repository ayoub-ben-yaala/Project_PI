/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PIproject.services;

import java.util.List;

/**
 *
 * @Ayoub Ben Yaala
 */
public interface IService <T> {
     public void ajouter(T user);
    public void modifier(T user);
    public void supprimer(T user);
        boolean supprimer1(T t);

    public List<T> afficher();
}





