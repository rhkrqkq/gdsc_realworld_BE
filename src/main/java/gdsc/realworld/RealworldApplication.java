package gdsc.realworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"gdsc.realworld.repository"}, exclude = { DataSourceAutoConfiguration.class} )
public class RealworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealworldApplication.class, args);
	}

}
