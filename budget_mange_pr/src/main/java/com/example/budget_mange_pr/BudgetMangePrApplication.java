package com.example.budget_mange_pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EntityScan(basePackages = {"com.example.budget_mage_pr.entities"})

public class BudgetMangePrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetMangePrApplication.class, args);
	}

}
