package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Controllers.Admin.InsertCarController;
import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Repository.Interfaces.CarRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryCar;
import com.jmc.AutoSalon.Services.Interfaces.CarServiceInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class carService implements CarServiceInterface {

    private CarRepositoryInterface carRepository;

    public carService() {
        this.carRepository = new RepositoryCar();
    }

    @Override
    public Cars insert_car(String name, String model, String type, double price, String color,
                           double maxSpeed, int yearMade,int quantity, String carImage) throws SQLException {
        Date insertedOn = Date.valueOf(LocalDate.now());
        Date updatedOn = Date.valueOf(LocalDate.now());
        boolean status = CarAuthService.CarAdd(name,model,type,String.valueOf(price),color,String.valueOf(maxSpeed),
                String.valueOf(yearMade),String.valueOf(quantity),carImage);
        if (status) {
            CreateCarsDto cars = new CreateCarsDto(name, model, type, price, color, maxSpeed, yearMade,quantity, carImage, insertedOn, updatedOn);
            this.carRepository.insert(cars);
            return RepositoryCar.getByCarName(name,model,type,yearMade);
        }else {
            return null;
        }

    }

    @Override
    public Cars update_car(int serial,String type, String price, String speed, String quantity, String image) throws SQLException {
        Date updated = Date.valueOf(LocalDate.now());
        Cars car = RepositoryCar.getById(serial);
        String name = car.getName();
        String model = car.getModel();
        int yearMade = car.getYearMade();
        String color = car.getColor();
        Date inserted = car.getInsertedOn();


        CreateCarsDto cars = new CreateCarsDto(name,model,type,Double.valueOf(price),color,Double.valueOf(speed),
                Integer.valueOf(yearMade),Integer.valueOf(quantity),image,inserted,updated);

        this.carRepository.update(cars,serial);
        return null;
    }
    @Override
    public void deleteCar(int id) throws SQLException {
        this.carRepository.deleteCar(id);
    }

    @Override
    public void fillCarTable(TableView<Cars> tbl) throws SQLException {
        this.carRepository.getAllCars(tbl);
    }

}