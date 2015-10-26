package org.eja.montecarlo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private ScatterChart<Number, Number> grafica;

    @FXML
    private TextField maxValue, points, x, y, r, p;

    @FXML
    private Button calcularBtn;

    private XYChart.Series series1;
    private XYChart.Series series2;
    private ParallelTransition animation;

    public void loadData(int points, final double max, final double x, final double y, final double r, final double p) {

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Point p1 = getPoint(max, new Point(x, y), r, p);
                        series1.getData().add(new XYChart.Data(p1.x, p1.y));
                        Point p2 = getPoint(max, new Point(x, y), r, p);
                        series2.getData().add(new XYChart.Data(p2.x, p2.y));
                    }
                })
        );
        timeline.setCycleCount(points);
        timeline.setDelay(Duration.seconds(0.5));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calcularBtn.setDisable(false);
            }
        });
        animation = new ParallelTransition();
        animation.getChildren().addAll(timeline);
        animation.play();
    }

    private Point getPoint(double max, Point origin, double r, double p) {

        double angle = Math.random() * 360;
        if (Math.random() < p / 100) {
            max = r;
        }

        max = Math.random() * max;

        double x = origin.x + max * Math.cos(angle);
        double y = origin.y + max * Math.sin(angle);
        /*
         double point = Math.random() * max;
         point *= Math.random() < 0.5 ? -1 : 1;*/
        return new Point(x, y);
    }

    @FXML
    public void calcular() {

        series1.getData().clear();
        series2.getData().clear();

        loadData(
                Integer.valueOf(points.getText()),
                Double.valueOf(maxValue.getText()),
                Double.valueOf(x.getText()),
                Double.valueOf(y.getText()),
                Double.valueOf(r.getText()),
                Double.valueOf(p.getText())
        );

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        series1 = new XYChart.Series();
        series1.setName("Serie 1");

        series2 = new XYChart.Series();
        series2.setName("Serie 2");

        grafica.getData().addAll(series1, series2);

        calcular();
    }
}
