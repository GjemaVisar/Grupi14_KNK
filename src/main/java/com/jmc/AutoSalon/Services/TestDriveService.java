package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Repository.Interfaces.TestDriveRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryTestDrive;
import com.jmc.AutoSalon.Services.Interfaces.TestDriveServiceInterface;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestDriveService implements TestDriveServiceInterface {

    public static String PopupMessage;
    private TestDriveRepositoryInterface testDriveRepo;

    public TestDriveService(){
        this.testDriveRepo = new RepositoryTestDrive();
    }
    @Override
    public void add_test_drive(int user, int car, Date date_picked) {
        LocalDate today_date = LocalDate.now();
        LocalDate next_week = today_date.plusDays(14);

        if(date_picked.toLocalDate().isBefore(today_date)){
            TestDriveService.PopupMessage = "You cannot choose date from the past. Please choose today or dates in the future!";
            Model.getInstance().getViewFactory().showAlert("Date picking error",TestDriveService.PopupMessage);
            return;
        }else if(date_picked.toLocalDate().isAfter(next_week)){
            TestDriveService.PopupMessage = "Reservations after two weeks aren't yet available. Please pick a reservation during this interval!";
            Model.getInstance().getViewFactory().showAlert("Date picking error",TestDriveService.PopupMessage);
            return;
        }
        boolean status = this.testDriveRepo.is_date_car_busy(car,date_picked);
        if(status){
            TestDriveService.PopupMessage = "Unfortunately there already is a reservation for this car and date. Feel free" +
                    "to choose other days!";
            Model.getInstance().getViewFactory().showAlert("Reservation error!",TestDriveService.PopupMessage);
        }else{
            this.testDriveRepo.insertTestDrive(user,car,date_picked);
            TestDriveService.PopupMessage = "Reservation made successfuly. We wait for you at our offices!";
            System.out.println(TestDriveService.PopupMessage);
        }
    }

    @Override
    public void fill_xy_chart(XYChart.Series<String, Number> series) throws SQLException {
        this.testDriveRepo.displayReservationsPerDayOfWeek(series);
    }
}
