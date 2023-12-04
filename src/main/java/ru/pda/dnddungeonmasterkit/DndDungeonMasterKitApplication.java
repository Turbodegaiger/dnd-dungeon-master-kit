package ru.pda.dnddungeonmasterkit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DndDungeonMasterKitApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DndDungeonMasterKitApplication.class);

		builder.headless(false);
		builder.run(args);
	}
}
