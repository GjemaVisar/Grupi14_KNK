package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Repository.Interfaces.TestDriveRepositoryInterface;
import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.*;

public class RepositoryTestDrive implements TestDriveRepositoryInterface {
    @Override
    public void insertTestDrive(int user_id, int car_id, Date date_picked) throws SQLException {
        String sql = "INSERT INTO testing_appointment(user_id,car_id,data_rezervimit) VALUES (?,?,?)";
        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setInt(1,user_id);
            stm.setInt(2,car_id);
            stm.setDate(3,date_picked);
            stm.executeUpdate();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }

    @Override
    public boolean is_date_car_busy(int car_id, Date date_picked) throws SQLException {
        String sql = "SELECT * from testing_appointment WHERE car_id = ? and data_rezervimit = ?";
        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);){
            stm.setInt(1,car_id);
            stm.setDate(2,date_picked);
            ResultSet res = stm.executeQuery();
            if(res.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return true;
    }


}
