package com.jmc.AutoSalon.Services;

import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositorySales;
import com.jmc.AutoSalon.Services.Interfaces.SalesServiceInterface;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.SQLException;

public class salesService implements SalesServiceInterface {
    private SalesRepositoryInterface salesRepository;
    public salesService(){
        this.salesRepository = new RepositorySales();
    }

    @Override
    public void create_pie_chart(ObservableList<PieChart.Data> list) throws SQLException {
        this.salesRepository.get_sales_Data(list);
    }


}
