package com.jmc.AutoSalon.Models.dto;

import java.sql.Date;

public class CreateUserDto {
    private String username;
    private String saltedPassword;
    private boolean is_admin;

    private Date date_registered;

    public CreateUserDto(String username,String saltedPassword, boolean is_admin,Date date_registered){
        this.username = username;
        this.saltedPassword = saltedPassword;
        this.is_admin = is_admin;
        this.date_registered = date_registered;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getSaltedPassword(){
        return this.saltedPassword;
    }


    public void setSaltedPassword(String saltedPassword){
        this.saltedPassword = saltedPassword;
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
