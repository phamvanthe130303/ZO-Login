package com.example.zologin;

import com.example.zologin.module.user.controller.CreateWebDriverController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZoLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoLoginApplication.class, args);
		CreateWebDriverController controller = new CreateWebDriverController();
		controller.CreateWebDriverDemo();
	}

}
