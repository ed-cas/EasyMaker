package com.easymaker.easymaker.Objetos;

import android.net.Uri;
import java.io.Serializable;


public class Usuario implements Serializable{
    private String email;
    private String name;
    private String carrier;
    private String university;
    private String ocupation;
    private transient Uri perfil_photo;
    private String id;
    private String skills;

    public Usuario(){
        email="";
        name="";
        carrier="";
        university="";
        ocupation="";
        perfil_photo=null;
        id="";
        skills="";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Uri getPerfil_photo() {
        return perfil_photo;
    }

    public void setPerfil_photo(Uri perfil_photo) {
        this.perfil_photo = perfil_photo;
        //Picasso.with(this).load(perfil_photo).into(profileImage);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
