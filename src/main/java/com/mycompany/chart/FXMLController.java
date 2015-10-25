package com.mycompany.chart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FXMLController implements Initializable {
    
    @FXML
    private ScatterChart<Number,Number> grafica;
        
    public void loadData(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Serie 1");
        

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Serie 2");
        
        for (int i = 0; i < 100; i++) {
            double xS1 = Math.random()*50;
            double yS1 = Math.random()*50;
            double xS2 = Math.random()*50;
            double yS2 = Math.random()*50;
            if(Math.random()>0.8){
                xS1*=-1;
                yS1*=-1;
            }
            if(Math.random()>0.8){
                xS2*=-1;
                yS2*=-1;
            }
                            
            
            series1.getData().add(new XYChart.Data(xS1, yS1));
            series2.getData().add(new XYChart.Data(xS2, yS2));
        }
        

        grafica.getData().addAll(series1, series2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadData();
        //panel.getChildren().add(createChart());
    }    
}
