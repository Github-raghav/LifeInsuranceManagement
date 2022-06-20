package com.Lims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.Lims")
public class LifeInsuranceManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeInsuranceManageApplication.class, args);
	}

}
