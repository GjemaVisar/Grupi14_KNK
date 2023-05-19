package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class userService implements UserServiceInterface {
    private UserRepositoryInterface userRepository;

    private static int current_user_id;

    public userService() {
        this.userRepository = new RepositoryUser();
    }

    @Override
    public User login(String username, String password) throws SQLException {
        User loginUser = RepositoryUser.getByUsername(username);
        if(loginUser == null){
            return null;
        }

        Boolean status = UserAuthService.login(loginUser,username,password);
        if(status){
            userService.current_user_id = loginUser.getId();
            return loginUser;
        }
        return null;
    }

    @Override
    public int get_user_id(){
        return userService.current_user_id;
    }
    @Override
    public User signup(String username, String password) throws SQLException {
        String saltedHash = PasswordHasher.hashPassword(password);
        boolean isAdmin = false;
        Date dateRegistered = Date.valueOf(LocalDate.now());
        CreateUserDto user = new CreateUserDto(username, saltedHash, isAdmin, dateRegistered);
        this.userRepository.insert(user);
        return RepositoryUser.getByUsername(username);
    }

    public User createClient(String username,String password,Boolean status) throws SQLException {
        String saltedHash = PasswordHasher.hashPassword(password);
        Date date_registered = Date.valueOf(LocalDate.now());
        CreateUserDto user = new CreateUserDto(username, saltedHash,status, date_registered);
        this.userRepository.insert(user);
        return RepositoryUser.getByUsername(username);

    }

    @Override
    public void fillUserTable(TableView<User> tbl,Boolean statusi) throws SQLException {
        this.userRepository.getAllUsers(tbl,statusi);
    }

    @Override
    public void deleteUser(int id) throws SQLException{
        this.userRepository.deleteUser(id);
    }
    @Override
    public String get_username() throws SQLException{
        String username = this.userRepository.getByUsernameId(current_user_id);

        return username;
    }

    @Override
    public List<Cars> your_car() throws SQLException{
        return this.userRepository.your_car(current_user_id);
    }

}