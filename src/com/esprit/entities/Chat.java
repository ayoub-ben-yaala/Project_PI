/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author gdallegi
 */
public class Chat {
    private int id_chat;
    private int id_livreur;
    private int id_pharmacie;
    private String message;


    public Chat(int id_chat, int id_livreur, int id_pharmacie, String message) {
        this.id_chat = id_chat;
        this.id_livreur = id_livreur;
        this.id_pharmacie = id_pharmacie;
        this.message = message;

    }

    // Getters and Setters
    public int getIdChat() {
        return id_chat;
    }

    public void setIdChat(int id_chat) {
        this.id_chat = id_chat;
    }

    public int getIdLivreur() {
        return id_livreur;
    }

    public void setIdLivreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public int getIdPharmacie() {
        return id_pharmacie;
    }

    public void setIdPharmacie(int id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    // Other methods
    public void envoyerMessageLivreur(String message) {
        // Logique pour envoyer le message au livreur
        System.out.println("Envoi du message au livreur " + id_livreur + ": " + message);
    }

    public void envoyerMessagePharmacie(String message) {
        // Logique pour envoyer le message à la pharmacie
        System.out.println("Envoi du message à la pharmacie " + id_pharmacie + ": " + message);
    }

    public void recevoirMessageLivreur(String message) {
        // Logique pour recevoir le message du livreur
        System.out.println("Réception du message du livreur " + id_livreur + ": " + message);
    }

    public void recevoirMessagePharmacie(String message) {
        // Logique pour recevoir le message de la pharmacie
        System.out.println("Réception du message de la pharmacie " + id_pharmacie + ": " + message);
    }
}
