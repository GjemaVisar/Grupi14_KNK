package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Views.AccountType;
import com.jmc.AutoSalon.Views.ViewFactory;

import java.sql.SQLException;
import java.util.Objects;

public class UserAuthService {

    public static String signup_error;

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
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";

        User user = RepositoryUser.getByUsername(username);
        if(user != null ) {
            UserAuthService.signup_error = "Sorry but the user exists!";
            System.out.println();
            return false;
        }else if(Objects.equals(username, "") || Objects.equals(email, "") || Objects.equals(pass, "") || Objects.equals(cpass, "")){
            UserAuthService.signup_error = "Please fill out all the fields";
            return false;
        }else if(!Objects.equals(pass, cpass)){
            UserAuthService.signup_error="Make sure that the password and confirm password are the same";
            return false;
        }else if (!pass.matches(passwordRegex) && pass != null) {
            UserAuthService.signup_error = "Password not matching";
            return false;
        }else if(!email.matches(emailRegex) && email != null){
            UserAuthService.signup_error = "Email not matching";
            return false;
        }else if(user == null && Objects.equals(pass, cpass)){
            System.out.println("You can register!!");
            return true;}

        return true;
    }

    public static boolean createClient(String username,String email,String pass,String cpass) throws SQLException {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        User user = RepositoryUser.getByUsername(username);
        if (Objects.equals(username, "") || Objects.equals(email, "") || Objects.equals(pass, "") || Objects.equals(cpass, "")) {
            UserAuthService.signup_error = "Please fill out all of the fields";
            return false;
        } else if (!Objects.equals(pass, cpass)) {
            UserAuthService.signup_error = "Make sure that the password and confirm password are the same";
            return false;
        }else if(user != null){
            UserAuthService.signup_error = "Sorry but the user exists!";
            return false;
        }else if (!pass.matches(passwordRegex) && pass!= null) {
            UserAuthService.signup_error = "Password not matching";
            return false;
        }else if(!email.matches(emailRegex) && email!= null){
            UserAuthService.signup_error = "Email not matching";
            return false;
        }else if(user == null && Objects.equals(pass, cpass)){
            System.out.println("You can register!!");
            return true;
        }
        return true;
    }


}
