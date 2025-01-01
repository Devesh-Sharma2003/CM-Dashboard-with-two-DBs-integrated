package in.kpmg.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("in.kpmg.cm.repo")
public class cmdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(cmdashboardApplication.class, args);
	}

}
