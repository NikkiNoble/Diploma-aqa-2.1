## Процедура запуска авто-тестов
_Первоначальная настройка стоит на СУБД PostgreSQL_
#### После git clone необходимо:
*_Файлы для запуска контейнера, банковского симулятора и приложения находятся в каталоге data - перед их запуском прописывается путь cd ./data_
1.  Запустить docker-compose.yml с помощью docker-compose up. Должны запуститься два контейнера (node-app и postgresql).
Отмечу, что в application.properties прописан не localhost, а IP 192.168.99.100 для использования Docker Toolbox. 
2. Запустить приложение aqa-shop.jar с помощью java -jar aqa-shop.jar
3. Запустить тесты gradlew test (clean test allureReport - если есть необходимость посмотреть отчеты Allure)

#### Чтобы запустить приложение на БД MySQL:

2. Заходим в файл application.properties и раскомментируем строку 3 spring.datasource.url=jdbc:mysql://192.168.99.100:3306/app, закомментируем строку 4 url=jdbc:postgresql
3. Заходим в java класс DBInteractions и раскомментируем строки 11-18 try connection, закомментируем строки 19-27 
1. Заходим в файл docker-compose.yml и раскомментируем строки с mysql 3-11, закомментируем строки с postgresql 12-19. Далее запускаем docker-compose.yml - docker-compose up.
1. Запускаем aqa-shop.jar. После настройки окружения запускаем автотесты.
