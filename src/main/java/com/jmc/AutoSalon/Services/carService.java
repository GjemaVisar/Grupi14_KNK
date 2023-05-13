package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Repository.Interfaces.CarRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryCar;
import com.jmc.AutoSalon.Services.Interfaces.CarServiceInterface;

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
                           double max_speed, int year_made, String car_image) throws SQLException {
        Date inserted_on = Date.valueOf(LocalDate.now());
        Date updated_on = Date.valueOf(LocalDate.now());
        boolean status = CarAuthService.CarAdd(name,model,type,String.valueOf(price),color,String.valueOf(max_speed),
                String.valueOf(year_made),car_image);
        if (status) {
            CreateCarsDto cars = new CreateCarsDto(name, model, type, price, color, max_speed, year_made, car_image, inserted_on, updated_on);
            this.carRepository.insert(cars);
            return RepositoryCar.getByCarName(name,model,type,year_made);
        } else {
            System.out.println("Car exists or credentials given wrong!");
            return null;
        }

    }
}