package com.example.myNutrition;

import com.example.myNutrition.common.dto.PermitAllProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PermitAllProperties.class)
public class myNutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(myNutritionApplication.class, args);
	}

}
