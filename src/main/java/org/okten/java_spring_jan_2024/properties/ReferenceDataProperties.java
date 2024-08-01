package org.okten.java_spring_jan_2024.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data

@Component
@ConfigurationProperties(prefix = "reference-data") // Анотація для автоматичного зв'язування конфігураційних значень з полями класу
public class ReferenceDataProperties {

//    private List<String> categories; //
    private List<Office> offices;
}

// @ConfigurationProperties: Ця анотація вказує, що поля цього класу будуть автоматично
//              заповнені значеннями з конфігураційного файлу, які починаються з префіксу
//              reference-data.

//@Component: Ця анотація вказує, що цей клас є Spring-компонентом, і він буде автоматично
//              створений і керований Spring контекстом.
// Анотація @Component позначає клас як компонент Spring, що означає, що Spring
// автоматично створює об'єкти цього класу і керує їхнім життєвим циклом.
// Це є частиною механізму управління залежностями у Spring.

            //Коли ви використовуєте @Component, Spring:
                    // - Автоматично створює екземпляри класу.
                    // - Ін’єкціює залежності для цього класу.
                    // - Дозволяє вам використовувати ці об'єкти в інших частинах вашого додатка, наприклад, через ін'єкцію залежностей.