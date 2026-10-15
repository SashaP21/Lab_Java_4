# Лабораторная работа № 3. Обработка исключений, работа с файлами и сериализация

## **Автор:** Пустозёрова Александра Дмитриевна
## **Группа:** ФИТ-251

## Структура проекта

```text
java-lab3/
├─ src/
│  ├─ app/
│  │  ├─ Main.java            
│  ├─ geometry2d/
│  │  ├─ Figure.java         
│  │  ├─ Circle.java          
│  │  └─ Rectangle.java       
│  ├─ geometry3d/
│  │  └─ Cylinder.java        
│  ├─ exceptions/
│  │  ├─ InvalidFigureException.java  
│  │  └─ FigureFormatException.java   
│  └─ storage/
│     ├─ FigureStorage.java           
│     ├─ TextFigureStorage.java       
│     └─ SerializedFigureStorage.java 
├─ data/                      
├─ README.md
└─ .gitignore
```

## Сборка и запуск

Из корня проекта:

```bash
javac -encoding UTF-8 -d out src/exceptions/*.java src/geometry2d/*.java src/geometry3d/*.java src/storage/*.java src/app/*.java
```

Приложение (аргументы: входной текстовый файл, выходной бинарный файл):

```bash
java -cp out app.Main data/figures.txt output.figures.bin
```

Без аргументов пути запрашиваются у пользователя:

```bash
java -cp out app.Main
```


## Текстовый формат фигур

Одна строка - одна фигура. Разделитель - пробел,
десятичный разделитель - точка. Регистр не важен, пустые строки пропускаются.

```text
circle <радиус>
rectangle <ширина> <высота>
```

## Поведение при ошибках чтения

При ошибке приложение прерывает загрузку и выбрасывает FigureFormatException 
(с вложенной причиной, например, NumberFormatException).
Все уже прочитанные к этому моменту фигуры отбрасываются и не возвращаются.
