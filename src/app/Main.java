package app;

import exceptions.FigureFormatException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.FigureStorage;
import storage.SerializedFigureStorage;
import storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static final double CYLINDER_HEIGHT = 5.0;

    public static void main(String[] args) {
        try {
            Path inputPath;
            Path outputPath;
            if (args.length >= 2) {
                inputPath = Path.of(args[0]);
                outputPath = Path.of(args[1]);
            } else {
                try (Scanner scanner = new Scanner(System.in)) {
                    inputPath = Path.of(ask(scanner, "Введите путь к входному текстовому файлу: "));
                    outputPath = Path.of(ask(scanner, "Введите путь к выходному бинарному файлу: "));
                }
            }
            run(inputPath, outputPath);
        } catch (FigureFormatException e) {
            System.out.println("Ошибка формата входного файла: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Причина: " + e.getCause());
            }
            System.out.println("Загрузка отклонена, список не получен.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден при десериализации: " + e.getMessage());
        } finally {
            System.out.println("Работа приложения завершена.");
        }
    }

    private static String ask(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }

    private static void run(Path inputPath, Path outputPath)
            throws IOException, FigureFormatException, ClassNotFoundException {

        FigureStorage textStorage = new TextFigureStorage();
        FigureStorage serializationStorage = new SerializedFigureStorage();

        List<Figure> figures = textStorage.read(inputPath);
        System.out.println("Прочитано фигур: " + figures.size());

        for (Figure figure : figures) {
            System.out.printf("%s — площадь: %.3f, периметр: %.3f%n",
                    figure, figure.area(), figure.perimeter());
        }

        if (figures.isEmpty()) {
            System.out.println("Список фигур пуст, цилиндр не создаётся.");
        } else {
            Figure base = figures.get(new Random().nextInt(figures.size()));
            Cylinder cylinder = new Cylinder(base, CYLINDER_HEIGHT);
            System.out.println("Случайно выбранное основание: " + base);
            System.out.printf("Высота цилиндра: %s, объём: %.3f%n",
                    CYLINDER_HEIGHT, cylinder.volume());
        }

        serializationStorage.write(outputPath, figures);
        System.out.println("Список из " + figures.size()
                + " фигур сохранён в бинарный файл: " + outputPath);
    }
}
