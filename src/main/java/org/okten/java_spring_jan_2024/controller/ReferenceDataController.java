package org.okten.java_spring_jan_2024.controller;

import lombok.RequiredArgsConstructor;

import org.okten.java_spring_jan_2024.properties.Office;
import org.okten.java_spring_jan_2024.properties.ReferenceDataProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reference-data")
@RequiredArgsConstructor
public class ReferenceDataController {


    // Отримання значення з application.yaml за допомогою анотації @Value
    // @Value("${reference-data.categories}"): Використовується для ін'єкції конкретного значення або списку значень безпосередньо з конфігураційного файлу в поле класу. Це підходить, коли потрібно отримати прості або окремі значення конфігурацій.
    @Value("${reference-data.categories}")
    private List<String> categories;


    // Ін'єкція залежності ReferenceDataProperties через конструктор
    // @ConfigurationProperties, що є в ReferenceDataProperties файлі: Використовуйте, коли у вас є складна
    // конфігурація з багатьма параметрами, які ви хочете згрупувати в об'єкт. Це особливо корисно, коли конфігурації використовуються в різних частинах програми.
    private final ReferenceDataProperties referenceDataProperties;  // Spring автоматично створює та передає об'єкт ReferenceDataProperties, оскільки він позначений анотацією @Component

    // Обробка GET-запиту на /reference-data/categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        // Повернення списку категорій, визначених у конфігурації
        return ResponseEntity.ok(categories);
    }

    // Обробка GET-запиту на /reference-data/offices з необов'язковим параметром "city"
    @GetMapping("/offices")
    public ResponseEntity<List<Office>> getOffices(@RequestParam(required = false) String city) {  // анотація @RequestParam(required = false)вказує, що параметр city є необов'язковим
        if (city != null) {
            // Фільтрація офісів за містом, якщо параметр "city" вказано
            return ResponseEntity
                    .ok(referenceDataProperties
                            .getOffices() // отримуємо список офісів
                            .stream() // створюємо потік з офісів
                            .filter(office -> Objects.equals(office.getAddress().getCity(), city)) // фільтруємо офіси за містом
                            .toList()); // перетворюємо результат в список
        } else {
            // Повернення всіх офісів, якщо параметр "city" не вказано
            return ResponseEntity.ok(referenceDataProperties.getOffices());
        }
    }

    // Обробка GET-запиту на /reference-data/offices/{name}
    @GetMapping("/offices/{name}")
    public ResponseEntity<Office> getOffice(@PathVariable String name) { // анотація дозволяє отримати частину URL як параметр методу.
        // Пошук офісу за назвою
        return ResponseEntity
                .of(referenceDataProperties
                        .getOffices() // отримуємо список офісів
                        .stream() // створюємо потік з офісів
                        .filter(office -> Objects.equals(office.getName(), name)) // фільтруємо офіси за ім'ям
                        .findFirst()); // повертаємо перший знайдений елемент
    }
}

// @RestController: Це анотація, яка поєднує в собі @Controller та @ResponseBody.
//                  Вона вказує, що цей клас є контролером і автоматично серіалізує об'єкти, які він повертає, у формат JSON.
//
// @RequestMapping("/reference-data"): Це анотація, яка визначає базовий URL-шлях для всіх методів у цьому контролері.
//
// @RequiredArgsConstructor: Це анотація з Lombok, яка автоматично генерує конструктор з усіма полями,
//                  позначеними як final. Це полегшує ін'єкцію залежностей через конструктор.
//
// @Value("${reference-data.categories}"): Ця анотація дозволяє отримати значення з файлу конфігурації
//                  application-store1.yaml і зберегти його у змінну categories.
//
// ResponseEntity: Це спеціальний об'єкт, який дозволяє повертати HTTP-відповіді з контролера,
//                  включаючи статус код та тіло відповіді.
//
// @GetMapping: Ця анотація вказує, що метод обробляє HTTP GET-запити на вказаний шлях.
//
// @RequestParam(required = false): Ця анотація вказує, що параметр city є необов'язковим.
//                  Якщо його не вказано, метод повертає всі офіси.
//
// @PathVariable: Ця анотація дозволяє отримати частину URL як параметр методу.
//                  У цьому випадку name — це змінна частина шляху.


//                              Пояснення Stream API методів
//stream(): Створює потік з колекції. Це початковий крок для роботи з Stream API.

//filter(Predicate<? super T> predicate): Використовується для фільтрації елементів потоку на основі заданої умови. Повертає новий потік з елементами, які відповідають умові.

//map(Function<? super T,? extends R> mapper): Перетворює кожен елемент потоку за допомогою заданої функції. Повертає новий потік з перетвореними елементами.

//collect(Collector<? super T,A,R> collector): Використовується для перетворення елементів потоку у колекцію або іншу структуру даних. Найчастіше використовуються колектори для збору в List, Set, Map тощо.

//toList(): Новий метод Java 16+, який дозволяє швидко зібрати елементи потоку у список. Альтернатива collect(Collectors.toList()).

//findFirst(): Повертає Optional з першим елементом потоку, якщо такий існує. Якщо потік порожній, повертає Optional.empty().

//forEach(Consumer<? super T> action): Виконує задану дію для кожного елемента потоку.
