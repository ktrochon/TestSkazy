package kevin.trochon.resolveur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "kevin.trochon.resolveur")
public class ResolveurApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolveurApplication.class, args);
	}

}
