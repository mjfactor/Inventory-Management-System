package com.example.invetorysystem;

public class ReportManager {
    private static ReportManager instance;

    public static ReportManager getInstance(){
        if(instance == null){
            instance = new ReportManager();
        }
        return instance;
    }
    private ReportManager(){

    }

}
