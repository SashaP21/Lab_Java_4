package geometry2d;

import exceptions.InvalidFigureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleTest {

    private static final double EPSILON = 1e-9;

    @Test
    void areaOfThreeByFourRectangleIsTwelve() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        double actual = rectangle.area();

        assertEquals(12.0, actual, EPSILON);
    }

    @Test
    void areaOfSquareWithSideFiveIsTwentyFive() {
        Rectangle rectangle = new Rectangle(5.0, 5.0);

        double actual = rectangle.area();

        assertEquals(25.0, actual, EPSILON);
    }

    @Test
    void areaOfTenByTwoAndHalfRectangleIsTwentyFive() {
        Rectangle rectangle = new Rectangle(10.0, 2.5);

        double actual = rectangle.area();

        assertEquals(25.0, actual, EPSILON);
    }

    @Test
    void perimeterOfThreeByFourRectangleIsFourteen() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        double actual = rectangle.perimeter();

        assertEquals(14.0, actual, EPSILON);
    }

    @Test
    void perimeterOfSquareWithSideFiveIsTwenty() {
        Rectangle rectangle = new Rectangle(5.0, 5.0);

        double actual = rectangle.perimeter();

        assertEquals(20.0, actual, EPSILON);
    }

    @Test
    void perimeterOfTenByTwoAndHalfRectangleIsTwentyFive() {
        Rectangle rectangle = new Rectangle(10.0, 2.5);

        double actual = rectangle.perimeter();

        assertEquals(25.0, actual, EPSILON);
    }

    @Test
    void createdRectangleKeepsGivenWidthAndHeight() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        assertEquals(3.0, rectangle.getWidth(), EPSILON);
        assertEquals(4.0, rectangle.getHeight(), EPSILON);
    }

    @Test
    void createdSquareKeepsGivenSideInBothGetters() {
        Rectangle square = new Rectangle(5.0, 5.0);

        assertEquals(5.0, square.getWidth(), EPSILON);
        assertEquals(5.0, square.getHeight(), EPSILON);
    }

    @Test
    void constructorRejectsZeroWidth() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(0.0, 4.0));
    }

    @Test
    void constructorRejectsNegativeWidth() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(-3.0, 4.0));
    }

    @Test
    void constructorRejectsNotANumberWidth() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(Double.NaN, 4.0));
    }

    @Test
    void constructorRejectsPositiveInfinityWidth() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(Double.POSITIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsNegativeInfinityWidth() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(Double.NEGATIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsZeroHeight() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(3.0, 0.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(3.0, -4.0));
    }

    @Test
    void constructorRejectsNotANumberHeight() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(3.0, Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityHeight() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(3.0, Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityHeight() {
        assertThrows(InvalidFigureException.class, () -> new Rectangle(3.0, Double.NEGATIVE_INFINITY));
    }

    @Test
    void toStringContainsFigureTypeWidthAndHeight() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        String description = rectangle.toString();

        assertTrue(description.contains("Прямоугольник"));
        assertTrue(description.contains("ширина"));
        assertTrue(description.contains("3.0"));
        assertTrue(description.contains("высота"));
        assertTrue(description.contains("4.0"));
    }
}