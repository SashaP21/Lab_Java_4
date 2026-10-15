package storage;

import geometry2d.Figure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SerializedFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path))) {
            Object object = input.readObject();
            if (!(object instanceof List)) {
                throw new IOException("Файл не содержит список: " + path);
            }
            List<?> list = (List<?>) object;
            for (Object element : list) {
                if (!(element instanceof Figure)) {
                    throw new IOException("Элемент списка не является фигурой: "
                            + (element == null ? "null" : element.getClass().getName()));
                }
            }
            List<Figure> figures = (List<Figure>) object;
            return figures;
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))) {
            output.writeObject(figures);
        }
    }
}
