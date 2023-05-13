package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.Objects;

public class userService implements UserServiceInterface {
    private UserRepositoryInterface userRepository;

    public userService() {
        this.userRepository = new RepositoryUser();
    }

    @Override
    public User login(String username, String password) throws SQLException {
        User loginUser = RepositoryUser.getByUsername(username);
        System.out.println(loginUser.getSaltedHash());
        if(loginUser == null){
            return null;
        }
        boolean pass_match = PasswordHasher.checkPassword(password,loginUser.getSaltedHash());
        if(pass_match){
            return loginUser;
        }
        return null;
    }

    @Override
    public User signup(String username,String password) throws SQLException {
            String saltedHash = PasswordHasher.hashPassword(password);
            Boolean is_admin = false;
            Date date_registered = Date.valueOf(LocalDate.now());
            CreateUserDto user = new CreateUserDto(username, saltedHash,is_admin, date_registered);
            this.userRepository.insert(user);
            return RepositoryUser.getByUsername(username);

    }
}