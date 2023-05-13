package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Repository.RepositoryCar;

import java.sql.SQLException;

public class CarAuthService {

    public static boolean CarAdd(String name,String model, String type,String price,String color,String speed,
                                 String year,String image) throws SQLException {
        Cars car = RepositoryCar.getByCarName(name,model,type,Integer.valueOf(year));
        if(car != null){
            return false;
        }else if(Double.valueOf(speed)>500 | year.length() !=4 | Integer.valueOf(year)<2010 | Double.valueOf(price) < 0){
            return false;
        }else {
            return true;
        }
    }
}
