package com.myfavoriteplaces.myfavoriteplaces;

/**
 * Created by Karim on 10/06/2016.
 */
public class BD {
    private int _id;
    private String nom_place;
    private String type_place;
    private String address_place;
    private String description_place;

    public BD(String nom, String type, String address, String description){
        this.nom_place = nom;
        this.type_place = type;
        this.address_place = address;
        this.description_place = description;
    }

    public int getId_place(){ return _id; }

    public void setId_place(int id){
        this._id = id;
    }

    public String getNom_place(){
        return nom_place;
    }

    public void setNom_place(String nom){
        this.nom_place = nom;
    }

    public String getType_place(){
        return type_place;
    }

    public void setType_place(String type){
        this.type_place = type;
    }

    public String getAddress_place(){
        return address_place;
    }

    public void setAddress_place(String address){
        this.address_place = address;
    }

    public String getDescription_place(){ return description_place; }

    public void setDescription_place(String description){
        this.description_place = description;
    }
}
