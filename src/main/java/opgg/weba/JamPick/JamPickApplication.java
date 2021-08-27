package opgg.weba.JamPick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JamPickApplication {

	public static void main(String[] args) {
		SpringApplication.run(JamPickApplication.class, args);
	}

}
