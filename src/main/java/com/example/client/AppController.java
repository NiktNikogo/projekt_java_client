package com.example.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/app.fxml")
public class AppController implements Initializable {

	@Autowired
	private Client client;

	@FXML
	private BarChart<String, Number> chart;

	@FXML
	public void refresh(ActionEvent event) {
		updateChart();
	}

	@FXML
	public void quit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void about(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("O programiw");
		alert.setHeaderText("Klient Połącz 4");
		alert.setContentText("Program do wyświetlania statystyk serwisu Połącz 4.");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateChart();
	}

	private void updateChart() {
		chart.getData().clear();
		var series = new XYChart.Series<String, Number>();
		for (var user : client.getMostActive()) {
			if (user.name() == null) {
				continue;
			}
			series.getData().add(new XYChart.Data<>(user.name(), user.gamesPlayed()));
		}
		chart.getData().add(series);
	}
}
