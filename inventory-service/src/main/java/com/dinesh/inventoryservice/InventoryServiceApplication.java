package com.dinesh.inventoryservice;

import com.dinesh.inventoryservice.entity.Inventory;
import com.dinesh.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = Inventory.builder().skuCode("Iphone 15").quantity(4).build();
			Inventory inventory1 = Inventory.builder().skuCode("Iphone 14").quantity(0).build();
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
