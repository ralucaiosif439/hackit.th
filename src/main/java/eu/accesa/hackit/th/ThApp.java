package eu.accesa.hackit.th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ThApp {

	public static void main(String[] args) {
		SpringApplication.run(ThApp.class, args);
	}

}
