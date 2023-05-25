/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.entities;

/**
 *
 * @Ayoub Ben Yaala
 */
public abstract class User {
    
    
    private int idUser;
    private String UserName;
    private String Email;
    private String Password;
    private String Phone;
    private String Adress;

    public User(int idUser) {
        this.idUser = idUser;
    }

    
    
    public User(String UserName, String Email, String Password, String Phone, String Adress) {
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
        this.Adress = Adress;
        
    }

    public User(String UserName, String Email, String Phone, String Adress) {
        this.UserName = UserName;
        this.Email = Email;
        this.Phone = Phone;
        this.Adress = Adress;
    }

    public User(int idUser, String UserName, String Email, String Password, String Phone, String Adress) {
        this.idUser = idUser;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
        this.Adress = Adress;
    }



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

   

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", UserName=" + UserName + ", Email=" + Email + ", Password=" + Password + ", Phone=" + Phone + ", Adress=" + Adress +'}';
    }

  
}
