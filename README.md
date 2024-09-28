# Запуск проекта
- Клонируйте репозиторий:
`git clone https://github.com/asssisst781/Dodopizza.git`
- Откройте проект в любой среде разработки
- Настройте файл application.properties:

> spring.datasource.username=YourUsername
> 
> spring.datasource.password=YourPassword
> 
>server.port=YourPort

 - Запустите проект, выбрав PizzeriaApplication в правом верхнем углу
 
![image info](https://github.com/asssisst781/Dodopizza/blob/main/images/Screenshot_4.png)

- Проект запуститься на вашем локальном сервере и будет доступен по адресу http://localhost:YourPort

# Архитектура проекта
- entities: содержит сущности, с которыми производится работа
- services: содержит классы, содержащие всю логику работы
- security: содежит класс для конфигурации безопасности и возможности функции авторизации
- repositories: репозитории для работы с таблицами
- mappers: классы для оборачивания сущностей в DTO и наоборот
- DTO: классы для обертки данных с целью использования в подсистемах проекта
- controllers: контроллеры для выполнения HTTP запросов
- config: классы для конфигурации других функций
- client: клиент для HTTP запросов
- PDFGenerator: класс генерирующий PDF файлы при заказе (чеки)
- resources/documents: тут хранятся чеки при выполнении запросов order
# Стек технологий
- Java 17: ЯП
- Spring boot 3.3.3: платформа
- Spring Security: для функции авторизации
- Spring Data JPA: для простого использования таблиц
- Hibernate: для отображения классов на таблицы базы данных
- Liquibase: для миграции баз данных 
- Apache PDFBox: для работы с PDF файлами
- Lombock: для лаконичного кода
- MapStruct: для обертки классов
- Gradle: для сборки
- JUnit: для тестирования
- Insomnia: REST API CLIENT

# Энд поинты
## auth
- register: Регистрация
	> 	"email" : "почта",
	>	"password" : "пароль"

- login: Авторизация
	>	"email" : "почта",
	>	"password" : "пароль"
- resetPassword: Сброс пароля
	>	"email" : "почта"
	>На указанную почту вы получите следующий эндпоинт:
	
	> localhost:YourPort/auth/createNewPassword?email=joebiden74@gmail.com&token=733546f8-bea1-4239-a729-fd8ce49af2cd
	> вставьте его в свой REST API клиент со следующим телом
		>"password" : "новый пароль"

## admin 
> (роль ROLE_ADMIN надо прописать вручную в таблице users_permissions)

- banUser: заблокировать юзера (нужно обладать ролью ROLE_ADMIN, не иметь роль ROLE_BANNED и быть авторизованным)
>	 "email" : "почта блокируемого"
- unbanUser: раблокировать юзера (нужно обладать ролью ROLE_ADMIN, не иметь роль ROLE_BANNED и быть авторизованным)
> 	"email" : "почта разблокируемого"
- addPizza: добавить пиццу на витрину (нужно обладать ролью ROLE_ADMIN, не иметь роль ROLE_BANNED и быть авторизованным)
>		"title" : "любое название",
>		"ids" : [
>		"1",
>		"2",
> 		 ...
>		]

- removePizza: убрать пиццу с витрины (нужно обладать ролью ROLE_ADMIN, не иметь роль ROLE_BANNED и быть авторизованным)
>	 "ids" : [
>		"1",
>		"2",
>		...
>		]
все id продуктов в файле 14_09_2024_first_migration.sql

## geo
- userLocation: получает данные о пользователе (нужно обладать ролью ROLE_USER, не иметь роль ROLE_BANNED и быть авторизованным)
> GET запрос

## order

- makeOrder: сделать заказ (нужно обладать ролью ROLE_USER, не иметь роль ROLE_BANNED и быть авторизованным)
> "pizzaIdDTO": {
> "id": "айди пиццы"
> },
> "paymentMethod": {
> "number": "16-значное число на 4/5",
> "date": "месяц/день",
> "cvv": "3/4 цифры"
> }
>  все id пицц лежат в таблице pizzas
	После выполнения заказа генерирует чек в виде PDF файла. Хранится в папке resources/documents
	
	
- makeOrderCustom: сделать заказ, создав свою пиццу (нужно обладать ролью ROLE_USER, не иметь роль ROLE_BANNED и быть авторизованным)
>	 "pizzaAddDTO": {
        "title": "любое название",
				"ids" : [
					"айди продукта",
					"2",
					"3"
				]
	    },
		"paymentMethod": {
   	"number": "16-значное число на 4/5",
   	  "date": "месяц/день",
		 "cvv": "3/4 цифры"
  	  }
	> все id продуктов лежат в таблице products
	После выполнения заказа генерирует чек в виде PDF файла. Хранится в папке resources/documents

# Роли
- ROLE_ADMIN: может банить юзеров и добавлять/удалять пиццу с прилавка (id = 1)
-  ROLE_USER: может заказывать пиццу/заказывать свою созданную пиццу (id = 2)
- ROLE_BANNED: забанненый юзер, ни к чему не имеет доступа (id = 100)
- auth: единственный эндпоинт не требующий авторизации по http basic

# Тестирование
## Для  тестирования нажмите PizzeriaApplicationTests 

![image info](https://github.com/asssisst781/Dodopizza/blob/main/images/Screenshot_5.png)

# Контакты
### 228islamsabirov1337@gmail.com












