package com.jmc.AutoSalon.Models;
import java.sql.Date;
public class User {
    private int id;
    private String username;
    private String saltedHash;
    private boolean is_admin;
    private Date date_registered;
    public User(int id, String username, String saltedHash,boolean is_admin,Date date_registered) {
        this.id = id;
        this.username = username;
        this.saltedHash = saltedHash;
        this.is_admin = is_admin;
        this.date_registered = date_registered;
    }
    public User(){}
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSaltedHash() {
        return saltedHash;
    }
    public void setSaltedHash(String saltedHash) {
        this.saltedHash = saltedHash;
    }
    public void set_admin_status(boolean is_admin){
        this.is_admin = is_admin;
    }
    public boolean get_admin_status(){
        return this.is_admin;
    }
    public void setDate_registered(Date date_registered){
        this.date_registered = date_registered;
    }
    public Date getDate_registered(){
        return this.date_registered;
    }
}