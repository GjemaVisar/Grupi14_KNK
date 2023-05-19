package com.jmc.AutoSalon.Services.Interfaces;

import java.sql.Date;

public interface TestDriveServiceInterface  {
    void add_test_drive(int user, int car, Date date_picked);
}
