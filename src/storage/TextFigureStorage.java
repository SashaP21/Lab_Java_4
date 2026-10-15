package storage;

import exceptions.FigureFormatException;
import exceptions.InvalidFigureException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class TextFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, FigureFormatException {
        List<Figure> figures = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                figures.add(parseLine(line, lineNumber));
            }
        }
        return figures;
    }

    private Figure parseLine(String line, int lineNumber) throws FigureFormatException {
        String[] parts = line.trim().split("\\s+");
        try {
            switch (parts[0].toLowerCase()) {
                case "circle":
                    requireCount(parts, 2, lineNumber);
                    return new Circle(Double.parseDouble(parts[1]));
                case "rectangle":
                    requireCount(parts, 3, lineNumber);
                    return new Rectangle(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                default:
                    throw new FigureFormatException(
                            "Строка " + lineNumber + ": неизвестный тип фигуры '" + parts[0] + "'");
            }
        } catch (NumberFormatException e) {
            throw new FigureFormatException(
                    "Строка " + lineNumber + ": не удалось преобразовать число", e);
        } catch (InvalidFigureException e) {
            throw new FigureFormatException(
                    "Строка " + lineNumber + ": недопустимые параметры фигуры", e);
        }
    }

    private void requireCount(String[] parts, int expectedCount, int lineNumber)
            throws FigureFormatException {
        if (parts.length != expectedCount) {
            throw new FigureFormatException("Строка " + lineNumber + ": для фигуры '" + parts[0]
                    + "' ожидается параметров: " + (expectedCount - 1)
                    + ", получено: " + (parts.length - 1));
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Figure figure : figures) {
                if (figure instanceof Circle circle) {
                    writer.write("circle " + circle.getRadius());
                } else if (figure instanceof Rectangle rectangle) {
                    writer.write("rectangle " + rectangle.getWidth() + " " + rectangle.getHeight());
                } else {
                    throw new IOException("Неизвестный тип фигуры для записи: "
                            + figure.getClass().getName());
                }
                writer.newLine();
            }
        }
    }
}
