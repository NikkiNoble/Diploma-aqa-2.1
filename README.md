## Процедура запуска авто-тестов
_Первоначальная настройка стоит на СУБД PostgreSQL_
#### После git clone необходимо:
*_Файлы для запуска контейнера, банковского симулятора и приложения находятся в каталоге data - перед их запуском прописывается путь cd ./data_
1.  Запустить `docker-compose.yml` с помощью `docker-compose up`. Должны запуститься два контейнера (node-app и postgresql).
Отмечу, что в `application.properties` прописан не localhost, а IP 192.168.99.100 для использования Docker Toolbox. 
2. Запустить приложение `aqa-shop.jar` с помощью `java -jar aqa-shop.jar`
3. Запустить тесты `gradlew test` (`clean test allureReport` - если есть необходимость посмотреть отчеты Allure)

#### Чтобы запустить приложение на БД MySQL:

2. Заходим в файл `application.properties` в строке 3 прописываем `spring.datasource.url=jdbc:mysql://192.168.99.100:3306/app`
3. Заходим в java класс `DBInteractions` и в строках 10-17 в блоке _try-catch_ прописываем 
`connection = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app", "app", "pass");`
1. Заходим в файл `Dockerfile` в строке 1 прописываем `FROM node:8.0.19`
1. Заходим в файл `docker-compose.yml` и в services вместо `postgreSQL` вставляем следующие настройки
 `mysql:
                                                                                   image: mysql:8.0.18
                                                                                     ports:
                                                                                       - '3306:3306'
                                                                                     environment:
                                                                                       - MYSQL_RANDOM_ROOT_PASSWORD=yes
                                                                                       - MYSQL_DATABASE=app
                                                                                       - MYSQL_USER=app
                                                                                       - MYSQL_PASSWORD=pass.`
 Далее запускаем _docker-compose.yml_ - `docker-compose up`.
1. Запускаем `aqa-shop.jar`. После настройки окружения запускаем автотесты.