## URLShortener - сервис, который укорачивает длинные ссылки 
[![Build Status](https://github.com/eaxdev/URLShortener/workflows/build/badge.svg)](https://github.com/eaxdev/URLShortener/actions)

Данное приложение представляет собой сервис по укорачиванию длинных ссылок, подобно https://goo.gl/ или https://vk.cc/. 
Приложение самодостаточное, запускается из командной строки и не требует отдельно установленных servlet контейнеров, application серверов и т.п. 

## Используемые технологии и фреймворки:

* `Spring Boot`
* `Spring Data JPA`
* `Liquibase` - для управления миграциями базы данных
* `Thymeleaf` - html шаблонизатор
* `Twitter Bootstrap` - для разметки
* [Selenide](https://github.com/selenide/selenide) для UI тестов
* [TestContainers](https://github.com/testcontainers/testcontainers-java/) для запуска UI тестов в докер контейнере

Используется СУБД `H2`. Для тестов используется `H2DB`

### Автоматизация инфраструктуры:
  * `Maven`
  * `TravisCI`
  * `Docker`
  
  ## Как запустить
  
  Для сборки используется `Maven`:
  
  ```sh
  mvn clean package
  ```
  ### Запускаем:
  ```sh
  java -jar ./target/url-shortener.jar
  ```
 Приложение запустится на порту по умолчанию - `8080`
 
 ## Запуск, используя Docker
  
  Необходим установленный `Docker` и `docker-compose`
  
  Команда: 
  
  ```sh
  docker-compose up -d 
  ```
  запускает `docker` контейнер с приложением на 8080 порту
  
  ## Результат работы:
  
  Приложение имеет как веб интерфейс, так и REST сервис:
  
  ```sh
 pi@pi-VirtualBox:~$ http POST http://192.168.1.2:8080/add/rest link=http://ya.ru
 HTTP/1.1 200 
 Content-Type: application/json;charset=UTF-8
 Date: Sat, 02 Sep 2017 16:08:53 GMT
 Transfer-Encoding: chunked
 
 {
     "key": "ztV7", 
     "link": "http://ya.ru"
 }

  
  ```
  
 Веб интерфейс:
 ![Alt text](home.JPG?raw=true)
 ![Alt text](added.JPG?raw=true)
