package dmu.noonsub_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class NunsubBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(NunsubBackendApplication.class, args);
	}

}
