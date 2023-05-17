package com.jmc.AutoSalon.Models;

import com.jmc.AutoSalon.Views.ViewFactory;
import javafx.beans.value.ObservableValue;

public class Model {
    private static Model model;

    private final ViewFactory viewFactory;

    private ObservableValue<User> users;
    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

}
