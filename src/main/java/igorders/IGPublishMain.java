package igorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class IGPublishMain {

	public static void main(String[] args) {
		SpringApplication.run(IGPublishMain.class, args);
	}

}
