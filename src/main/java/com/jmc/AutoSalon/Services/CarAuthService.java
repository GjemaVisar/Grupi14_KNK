package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Repository.RepositoryCar;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class CarAuthService {

    public static String error_title;
    public static String message_title;

    public static boolean CarAdd(String name,String model, String type,String price,String color,String speed,
                                 String year,String quantity,String image) throws SQLException {
        ArrayList<String> validate = new ArrayList<String>();
        validate.addAll(Arrays.asList(name,model,type,price,color,speed,year,image));

        for(String member : validate){
            if(member == null){
                return false;

            }
        }
        Cars car = RepositoryCar.getByCarName(name,model,type,Integer.valueOf(year));
        if(car != null){
            return false;
        }else if(Double.valueOf(speed)>500 | year.length() !=4 | Integer.valueOf(year)<2010 | Double.valueOf(price) < 0
        | Integer.valueOf(quantity) < 1){
            return false;
        }else {
            return true;
        }
    }
}
