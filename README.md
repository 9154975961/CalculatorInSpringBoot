# Calculator In Spring Boot
 
#### Описание 
___

может считать большие выражения с сложением и вычитанием 
Пример: 2.15 - 3.4223 + 4.01 + 5.03 + 2.7

может считать большие выражения с Умножением и делением 
Пример: 7.4545 - 2.1588 / 2.5648

может считать используя скобки 
Пример: (7.4545 - 2.1588) / (2.5648 - 4) + 5.2

может извлекать корень 
Пример: (5-√225) + 10 + √16

может возводить в степень 
Пример: (7+2)^2

НЕ может возводить в степень и извлекать корень одновременно 
Пример: √(7+2)^2

НЕ может извлекать корень в корне одновременно 
Пример: √(8√(128+128))

### Endpoints:
___
```sh
http://localhost:8080/calculator
```

### Запуск .jar:
___
```sh
java -jar Calculator_In_Spring_Boot.jar
```