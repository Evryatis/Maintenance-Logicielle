package shape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class CercleTest {

    @Test
    void testSetAndGetCenterAndRadus() {
        Cercle c = new Cercle();
        Point center = new Point();
        center.setPos_x(7);
        center.setPos_y(11);

        c.setCenter(center);
        c.setRadus(15);

        assertSame(center, c.getCenter());
        assertEquals(7, c.getCenter().getPos_x());
        assertEquals(11, c.getCenter().getPos_y());
        assertEquals(15, c.getRadus());
    }

    @Test
    void testToString() {
        Cercle c = new Cercle();
        Point center = new Point();
        center.setPos_x(3);
        center.setPos_y(4);
        c.setCenter(center);
        c.setRadus(9);

        assertEquals("Cercle[center=Point[pos_x=3, pos_y=4], radus=9]", c.toString());
    }
}
