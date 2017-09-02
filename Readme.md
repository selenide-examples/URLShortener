## URLShortener - сервис, который укорачивает длинные ссылки 
[![Build Status](https://travis-ci.org/romask17/URLShortener.svg?branch=master)](https://travis-ci.org/romask17/URLShortener) [![codecov](https://codecov.io/gh/romask17/URLShortener/branch/master/graph/badge.svg)](https://codecov.io/gh/romask17/URLShortener) [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

Данное приложение представляет собой сервис по укорачиванию длинных ссылок, подобно https://goo.gl/ или https://vk.cc/. 
Приложение самодостаточное, запускаться из командной строки и не требует отдельно установленных servlet контейнеров, application серверов и т.п. 

## Используемые технологии и фреймворки:

* `Spring Boot`
* `Spring Data JPA`
* `Liquibase` - для управления миграциями базы данных
* `Thymeleaf` - html шаблонизатор
* `Twitter Bootstrap` - для разметки

Используется СУБД `PostgreSQL`

### Автоматизация инфраструктуры:
  * `Maven`
  * `TravisCI`
  * `Docker`
  
  ## Как запустить
  
  Для сборки используется `Maven`:
  
  ```sh
  mvn clean package
  ```
  Также понадобится `PostgreSQL`
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
  запускает `docker` контейнер с приложением на 8080 порту, а также `PostgreSQL` на порту `5432`
  
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