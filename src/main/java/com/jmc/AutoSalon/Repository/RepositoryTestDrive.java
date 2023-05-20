package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Repository.Interfaces.TestDriveRepositoryInterface;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class RepositoryTestDrive implements TestDriveRepositoryInterface {
    @Override
    public void insertTestDrive(int user_id, int car_id, Date date_picked) {
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
    public boolean is_date_car_busy(int car_id, Date date_picked) {
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

    @Override
    public void displayReservationsPerDayOfWeek(XYChart.Series<String,Number> series) throws SQLException {
        String sql = "SELECT COUNT(*) AS Total, DAYNAME(data_rezervimit) AS DayOfWeek " +
                "FROM testing_appointment " +
                "WHERE data_rezervimit >= ? " +
                "GROUP BY WEEKDAY(data_rezervimit)";


        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            LocalDate lastWeekMonday = LocalDate.now().minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            statement.setString(1, lastWeekMonday.format(DateTimeFormatter.ISO_DATE));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dayOfWeek = resultSet.getString("DayOfWeek");
                int total = resultSet.getInt("Total");

                // Adjust the day of week labels to start from Monday
                int dayIndex = (DayOfWeek.valueOf(dayOfWeek.toUpperCase()).getValue() + 5) % 7;
                String adjustedDayOfWeek = DayOfWeek.of(dayIndex + 1).toString();

                series.getData().add(new XYChart.Data<>(adjustedDayOfWeek, total));
            }

            //reservationsChart.getData().add(series);
        }
    }


}
