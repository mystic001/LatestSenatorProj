package com.example.latestsenatorproj;


import java.util.UUID;

public class Senator {
    private int id ;
    private UUID ud;
    private String name;
    private String email;
    private String phone;
    private String State;



    public Senator(int id , String name, String email, String phone, String state) {
        this(UUID.randomUUID());
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.State = state;
        this.id = id ;
    }

    public Senator(){

        ud = (UUID.randomUUID());
    }

    public Senator(UUID ud) {
        this.ud = ud ;
    }



    public  Senator(String name ){
        this.name = name;

    }



    public UUID getUd() {
        return ud;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
