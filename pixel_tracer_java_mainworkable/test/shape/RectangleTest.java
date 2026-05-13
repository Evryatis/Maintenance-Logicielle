package shape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class RectangleTest {

    @Test
    void testSetAndGetFields() {
        Rectangle rectangle = new Rectangle();
        Point p1 = new Point();
        p1.setPos_x(2);
        p1.setPos_y(3);

        rectangle.setP1(p1);
        rectangle.setWidth(20);
        rectangle.setHeight(10);

        assertSame(p1, rectangle.getP1());
        assertEquals(2, rectangle.getP1().getPos_x());
        assertEquals(3, rectangle.getP1().getPos_y());
        assertEquals(20, rectangle.getWidth());
        assertEquals(10, rectangle.getHeight());
    }

    @Test
    void testToString() {
        Rectangle rectangle = new Rectangle();
        Point p1 = new Point();
        p1.setPos_x(1);
        p1.setPos_y(1);
        rectangle.setP1(p1);
        rectangle.setWidth(5);
        rectangle.setHeight(7);

        assertEquals("Rectangle[p1=Point[pos_x=1, pos_y=1], width=5, height=7]", rectangle.toString());
    }
}
