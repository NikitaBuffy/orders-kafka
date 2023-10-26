# Очередь заказов с Apache Kafka

Тестовый проект, демонстрирующий использование Apache Kafka для асинхронной обработки данных и передачи сообщений между разными компонентами приложения.

Проект имитирует работу интернет-магазина и имеет следующую архитектуру:

- **Customer Service** - отвечает за принятие заказов от клиентов, первичную валидацию данных и размещение их в очереди для обработки. 
- **Processing Service** - занимается обработкой заказов по мере их поступления от Customer Service'a.
- **Notification Service** - отправляет уведомления клиентам о статусе их заказов по мере обработки.

![Структура проекта](assets/structure.jpg)

## Используемые технологии

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka)

## Инструкция по запуску

Для развертывания приложения:

```bash
  mvn clean package
```
```bash
  docker compose up
```

## Спецификация API

Проект имеет эндпоинт для принятия заказа из фронта:
```http
POST /api/v1/order/create
```

| Параметр     | Тип        | Описание               |
|:-------------|:-----------|:-----------------------|
| `customerId` | `Long`     | Header 'X-Customer-Id'   |
| `orderDto`   | `OrderDto` | Body JSON |

Пример тела запроса:
```json
{
  "customerName": "Nikita Pominov",
  "customerPhone": "+7 (999) 999-99-99",
  "customerEmail": "nikita@mail.ru",
  "items": [
    {
      "title": "Очки для душа",
      "price": "200"
    },
    {
      "title": "Шапочка для душа",
      "price": "500"
    }
  ],
  "deliveryAddress": "Санкт-Петербург, площадь Восстания, 2",
  "date": "2023-11-11 16:30"
}