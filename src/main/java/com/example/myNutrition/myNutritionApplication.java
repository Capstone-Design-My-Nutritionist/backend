package com.example.myNutrition;

import com.example.myNutrition.common.dto.PermitAllProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(PermitAllProperties.class)
@EnableJpaAuditing
public class myNutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(myNutritionApplication.class, args);
	}

}
