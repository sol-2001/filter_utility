# Утилита для фильтрации данных

## Стек
- **Java**: 17
- **Система сборки**: Gradle 8.10

## Зависимости
Сторонние библиотеки:

1) Picocli - парсинг аргументов командной строки. Версия - 4.7.6

```xml
implementation ("info.picocli:picocli:4.7.6")
```

2. johnrengelman.shadow. Версия - 7.1.2

## Полный build.gradle.kts

```
plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"


}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("info.picocli:picocli:4.7.6")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

application {
    mainClass = "org.example.App"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.App"
    }
}

tasks.shadowJar {
    archiveClassifier.set("all")
}

tasks.test {
    useJUnitPlatform()
}
```

## Пример запуска утилиты 

❯ java -jar filter_utility-1.0-SNAPSHOT-all.jar -f -a -p new_ -o /home/danil/results /home/danil/text.txt

### Реализованные опции

| Опция          | Описание                                                     |
| :------------- | :----------------------------------------------------------- |
| `-o <путь>`    | Директория для выходных файлов (по умолчанию: текущая)       |
| `-p <префикс>` | Префикс для имен файлов (например, `-p result_` → `result_integers.txt`) |
| `-a`           | Добавлять данные к существующим файлам                       |
| `-s`           | Краткая статистика (только количество элементов)             |
| `-f`           | Полная статистика                                            |



## Особенности реализации

1. Ошибки парсинга чисел (например, переполнение) выводятся в консоль, но не останавливают программу.