package com.example.propertyFromDb;

import com.example.propertyFromDb.property.ProjectProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyFromDbApplicationTests {
	
	@Autowired
	private ProjectProperties projectProperties;
	
	@Test
	void checkProperties() {
		String name = projectProperties.getName();
		String age = projectProperties.getAge();
		String color = projectProperties.getColor();

	}

}
