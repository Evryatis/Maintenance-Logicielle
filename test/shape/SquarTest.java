package shape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class SquarTest {

    @Test
    void testSetAndGetFields() {
        Squar squar = new Squar();
        Point p1 = new Point();
        p1.setPos_x(4);
        p1.setPos_y(8);

        squar.setP1(p1);
        squar.setLength(12);

        assertSame(p1, squar.getP1());
        assertEquals(4, squar.getP1().getPos_x());
        assertEquals(8, squar.getP1().getPos_y());
        assertEquals(12, squar.getLength());
    }

    @Test
    void testToString() {
        Squar squar = new Squar();
        Point p1 = new Point();
        p1.setPos_x(0);
        p1.setPos_y(0);
        squar.setP1(p1);
        squar.setLength(6);

        assertEquals("Squar[p1=Point[pos_x=0, pos_y=0], length=6]", squar.toString());
    }
}
