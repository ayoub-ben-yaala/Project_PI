
package com.esprit.services;

import java.util.List;

public interface IService<T> {

    public void ajouter(T t);
    public void modifier(T t);
    boolean supprimer(T t);
    public List<T> afficher();
    
}
