package com.jmc.AutoSalon.Models.dto;

public class CreateUserDto {
    private String username;
    private String salt;
    private String saltedPassword;
    private boolean is_admin;

    public CreateUserDto(String username,String salt, String saltedPassword,boolean is_admin){
        this.username = username;
        this.salt = salt;
        this.saltedPassword = saltedPassword;
        this.is_admin = is_admin;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getSalt(){
        return this.salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
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
}
