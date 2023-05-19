package com.jmc.AutoSalon.Repository.Interfaces;

import java.sql.Date;

public interface TestDriveRepositoryInterface {
    void insertTestDrive(int user_id, int car_id, Date date_picked);
    boolean is_date_car_busy(int car_id,Date date_picked);
}
