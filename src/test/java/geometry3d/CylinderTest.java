package geometry3d;

import exceptions.InvalidFigureException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CylinderTest {

    private static final double EPSILON = 1e-9;

    @Test
    void volumeWithCircleBaseRadiusTwoAndHeightFiveIsTwentyPi() {
        Cylinder cylinder = new Cylinder(new Circle(2.0), 5.0);

        double actual = cylinder.volume();

        assertEquals(Math.PI * 2.0 * 2.0 * 5.0, actual, EPSILON);
    }

    @Test
    void volumeWithCircleBaseRadiusHalfAndHeightTwoIsHalfPi() {
        Cylinder cylinder = new Cylinder(new Circle(0.5), 2.0);

        double actual = cylinder.volume();

        assertEquals(Math.PI * 0.5 * 0.5 * 2.0, actual, EPSILON);
    }

    @Test
    void volumeWithRectangleBaseThreeByFourAndHeightFiveIsSixty() {
        Cylinder cylinder = new Cylinder(new Rectangle(3.0, 4.0), 5.0);

        double actual = cylinder.volume();

        assertEquals(3.0 * 4.0 * 5.0, actual, EPSILON);
    }

    @Test
    void volumeWithRectangleBaseTenByTwoAndHalfAndHeightTwoIsFifty() {
        Cylinder cylinder = new Cylinder(new Rectangle(10.0, 2.5), 2.0);

        double actual = cylinder.volume();

        assertEquals(10.0 * 2.5 * 2.0, actual, EPSILON);
    }

    @Test
    void createdCylinderKeepsGivenCircleBaseAndHeight() {
        Figure base = new Circle(2.5);

        Cylinder cylinder = new Cylinder(base, 4.0);

        assertSame(base, cylinder.getBase());
        assertEquals(4.0, cylinder.getHeight(), EPSILON);
    }

    @Test
    void createdCylinderKeepsGivenRectangleBaseAndHeight() {
        Figure base = new Rectangle(3.0, 4.0);

        Cylinder cylinder = new Cylinder(base, 2.5);

        assertSame(base, cylinder.getBase());
        assertEquals(2.5, cylinder.getHeight(), EPSILON);
    }

    @Test
    void constructorRejectsNullBase() {
        assertThrows(InvalidFigureException.class, () -> new Cylinder(null, 5.0));
    }

    @Test
    void constructorRejectsZeroHeight() {
        assertThrows(InvalidFigureException.class, () -> new Cylinder(new Circle(1.0), 0.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        assertThrows(InvalidFigureException.class, () -> new Cylinder(new Circle(1.0), -5.0));
    }

    @Test
    void constructorRejectsNotANumberHeight() {
        assertThrows(InvalidFigureException.class, () -> new Cylinder(new Circle(1.0), Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityHeight() {
        assertThrows(InvalidFigureException.class,
                () -> new Cylinder(new Circle(1.0), Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityHeight() {
        assertThrows(InvalidFigureException.class,
                () -> new Cylinder(new Circle(1.0), Double.NEGATIVE_INFINITY));
    }

    @Test
    void toStringContainsFigureTypeCircleBaseAndHeight() {
        Cylinder cylinder = new Cylinder(new Circle(1.5), 4.0);

        String description = cylinder.toString();

        assertTrue(description.contains("Цилиндр"));
        assertTrue(description.contains("основание"));
        assertTrue(description.contains("Круг"));
        assertTrue(description.contains("высота"));
        assertTrue(description.contains("4.0"));
    }

    @Test
    void toStringContainsRectangleBaseDescription() {
        Cylinder cylinder = new Cylinder(new Rectangle(3.0, 4.0), 2.0);

        String description = cylinder.toString();

        assertTrue(description.contains("Цилиндр"));
        assertTrue(description.contains("Прямоугольник"));
        assertTrue(description.contains("2.0"));
    }
}
