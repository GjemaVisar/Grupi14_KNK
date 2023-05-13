package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.RepositoryUser;

import java.sql.SQLException;
import java.util.Objects;

public class UserAuthService {

    public static boolean register(String username,String email,String pass,String cpass) throws SQLException {
        User user = RepositoryUser.getByUsername(username);
        if(user != null ){
            System.out.println("Sorry but the user exists!");
            return false;
        }else if(!Objects.equals(pass, cpass)){
            System.out.println("Make sure that the password and confirm password are the same");
            return false;
        }else if(user == null && Objects.equals(pass, cpass)){
            System.out.println("You can register!!");
            return true;
        }
    return true;
    }
}
