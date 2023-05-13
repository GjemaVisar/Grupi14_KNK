package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;

import java.sql.SQLException;

public interface CarRepositoryInterface {
    public Cars insert(CreateCarsDto cars) throws SQLException;
}
