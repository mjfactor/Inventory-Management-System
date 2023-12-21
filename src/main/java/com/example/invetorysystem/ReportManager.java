package com.example.invetorysystem;
import net.sf.jasperreports.engine.*;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import java.util.jar.JarInputStream;

public class ReportManager {
    private static ReportManager instance;
    private JasperReport jasperReport;
    public static ReportManager getInstance(){
        if(instance == null){
            instance = new ReportManager();
        }
        return instance;
    }
    private ReportManager(){

    }

}
