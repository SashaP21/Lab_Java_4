package geometry2d;

import exceptions.InvalidFigureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircleTest {

    private static final double EPSILON = 1e-9;

    @Test
    void areaOfRadiusOneCircleIsPi() {
        Circle circle = new Circle(1.0);

        double actual = circle.area();

        assertEquals(Math.PI, actual, EPSILON);
    }

    @Test
    void areaOfRadiusSevenCircleIsPiTimesFortyNine() {
        Circle circle = new Circle(7.0);

        double actual = circle.area();

        assertEquals(Math.PI * 7.0 * 7.0, actual, EPSILON);
    }

    @Test
    void perimeterOfRadiusOneCircleIsTwoPi() {
        Circle circle = new Circle(1.0);

        double actual = circle.perimeter();

        assertEquals(2.0 * Math.PI, actual, EPSILON);
    }

    @Test
    void perimeterOfRadiusTwoAndHalfCircleIsFivePi() {
        Circle circle = new Circle(2.5);

        double actual = circle.perimeter();

        assertEquals(2.0 * Math.PI * 2.5, actual, EPSILON);
    }

    @Test
    void createdCircleKeepsGivenRadius() {
        Circle circle = new Circle(2.5);

        assertEquals(2.5, circle.getRadius(), EPSILON);
    }

    @Test
    void constructorRejectsZeroRadius() {
        assertThrows(InvalidFigureException.class, () -> new Circle(0.0));
    }

    @Test
    void constructorRejectsNegativeRadius() {
        assertThrows(InvalidFigureException.class, () -> new Circle(-2.5));
    }

    @Test
    void constructorRejectsNotANumberRadius() {
        assertThrows(InvalidFigureException.class, () -> new Circle(Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityRadius() {
        assertThrows(InvalidFigureException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityRadius() {
        assertThrows(InvalidFigureException.class, () -> new Circle(Double.NEGATIVE_INFINITY));
    }

    @Test
    void toStringContainsFigureTypeAndRadius() {
        Circle circle = new Circle(2.5);

        String description = circle.toString();

        assertTrue(description.contains("Круг"));
        assertTrue(description.contains("радиус"));
        assertTrue(description.contains("2.5"));
    }
}
