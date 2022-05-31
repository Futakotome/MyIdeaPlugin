package ${_package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"${_package}.${_artifactId}.infrastructure.dao"})
public class ServiceApplication {

public static void main(String[] args) {
SpringApplication.run(ServiceApplication.class, args);
}

}