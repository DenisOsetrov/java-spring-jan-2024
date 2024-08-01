package org.okten.java_spring_jan_2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoProjectApplication {

	// Це метод main, який запускає Spring Boot додаток
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoProjectApplication.class, args);
	}

}
// Пояснення анотації @SpringBootApplication:
		//Це комплексна анотація, яка об'єднує три інші:
			//@Configuration: Дозволяє використовувати методи з анотацією @Bean для визначення бінів в контексті Spring.
			//@EnableAutoConfiguration: Вказує Spring Boot автоматично налаштувати додаток на основі наявних залежностей.
			//@ComponentScan: Сканує пакет і всі підпакети, щоб знайти компоненти з анотаціями, такими як @Component, @Service, @Repository, і @Controller.
