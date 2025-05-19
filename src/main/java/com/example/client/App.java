package com.example.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class App extends Application {

	private ConfigurableApplicationContext applicationContext;

	@Value("${app.title}")
	private String applicationTitle;

	@Override
	public void start(Stage stage) {
		var fxWeaver = applicationContext.getBean(FxWeaver.class);
		stage.setScene(new Scene(fxWeaver.loadView(AppController.class), 800, 600));
		stage.setTitle(applicationTitle);
		stage.show();
	}

	@Override
	public void init() {
		applicationContext = new SpringApplicationBuilder(Main.class).run();
	}

	@Override
	public void stop() {
		applicationContext.close();
	}

}
