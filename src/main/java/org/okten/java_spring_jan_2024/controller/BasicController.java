package org.okten.java_spring_jan_2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



    // створюємо простий контролер, який обробляє HTTP-запит на URL /hello і повертає текст.
@Controller // Позначає клас як Spring MVC контролер.
@ResponseBody  // Результат методу повинен бути написаний прямо в тіло HTTP-відповіді.
    //@RestController // Спрощена анотація, що поєднує @Controller + @ResponseBody
public class BasicController {

    @GetMapping("/hello") // Визначає, що метод hello буде обробляти HTTP GET-запити за шляхом /hello
    public String hello() {
        // при перезапуску програми поверне return за посиланням http://localhost:8080/hello
        return "Hello from basic controller";
    }
}
