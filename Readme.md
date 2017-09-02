## URLShortener - сервис, который укорачивает длинные ссылки [![Build Status](https://travis-ci.org/romask17/URLShortener.svg?branch=master)](https://travis-ci.org/romask17/URLShortener)[![codecov](https://codecov.io/gh/romask17/URLShortener/branch/master/graph/badge.svg)](https://codecov.io/gh/romask17/URLShortener)

Данное приложение представляет собой сервис по укорачиванию длинных ссылок, подобно https://goo.gl/ или https://vk.cc/  

## Используемые технологии и фреймворки:

* Spring Boot
* Spring Data JPA
* Liquibase - для управления миграциями базы данных
* Thymeleaf - html шаблонизатор

### Автоматизация инфраструктуры:
  * Maven
  * TravisCI
  * Docker
  
  
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