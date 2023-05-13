package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Views.AccountType;
import com.jmc.AutoSalon.Views.ViewFactory;

import java.sql.SQLException;
import java.util.Objects;

public class UserAuthService {

    public static boolean login(User user,String username,String password){
        boolean pass_match = PasswordHasher.checkPassword(password,user.getSaltedHash());
        boolean status_check = false;
        if (user.get_admin_status() && Model.getInstance().getViewFactory().getLoginAccountType()== AccountType.ADMIN) {
            status_check = true;
        } else if (!user.get_admin_status() && Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
            status_check = true;
        }else{
            status_check = false;
        }

        if(pass_match && status_check){
            return true;}
        else{
            return false;}

    }

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
