# NotifyHub Admin Starter

Классический Spring Boot Starter, обеспечивающий административный интерфейс для управления процессом отправки уведомлений, мониторинга пользовательских устройств и анализа доставленных сообщений.
Модуль предназначен для интеграции в backend-сервисы, которым требуется централизованное администрирование push-уведомлений и логирование отправок.

## Возможности

Стартер предоставляет REST-API для следующих задач:
* получение списка устройств пользователей, подписанных на уведомления; 
* получение информации об отправленных сообщениях, включая фильтрацию по конкретному пользователю.

Все конечные точки объединены под базовым маршрутом: **`/api/admin`**

Добавьте зависимость в ваш build.gradle.kts:
```kotlin
    maven {
        url = uri("https://maven.pkg.github.com/lubimobile/notifyhub")
        credentials {
            username = "<GitHubUserName>"
            password = "ghp_..."
        }
    }

dependencies {
    implementation("io.lubimobile.notifyhub:notifyhub-admin-starter:<version>")
}
```

При использовании Maven:
```xml
<dependency>
    <groupId>io.lubimobile.notifyhub</groupId>
    <artifactId>notifyhub-admin-starter</artifactId>
    <version>YOUR_VERSION</version>
</dependency>
```

После добавления зависимости все административные endpoints будут автоматически зарегистрированы в вашем приложении благодаря автоконфигурации Spring Boot.

## REST API

### Получение всех подписанных пользовательских устройств

```rest
GET /api/admin/user-devices
```

Возвращает полный список устройств, зарегистрированных в системе и привязанных к пользователям.

```json
[
    {
        "id": "295b33a3-693a-4106-bca2-00ce3b000bbb",
        "userId": "64",
        "deviceToken": "fNISZl5eRHK2y...",
        "platform": "FCM",
        "deviceId": "64",
        "createdAt": "2025-10-06T16:54:39.008679Z",
        "updatedAt": "2025-10-06T22:09:09.367090Z"
    }
]
```

### Получение отправленных сообщений

```rest
GET /api/admin/sent-message
GET /api/admin/sent-message?userId=12345
```

Возвращает полный список уведомлений, отправленных всем пользователям или конкретному пользователю.

```JSON
[
    {
        "id": 1,
        "userId": "64",
        "result": {
            "success": true,
            "token": "fNISZl5eRHK2ySUOVTyA6N:...",
            "platform": "FCM",
            "messageId": "",
            "error": null
        },
        "createdAt": "2025-12-09T10:37:41.513839Z"
    }
]
```