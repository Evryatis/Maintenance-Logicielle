package layer;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import shape.Circle;
import shape.Shape;

import static org.junit.jupiter.api.Assertions.*;

class LayerTest {

    @Test
    void testSetAndGetId() {
        Layer layer = new Layer();
        layer.setId(5);
        assertEquals(5, layer.getId());
    }

    @Test
    void testSetAndGetName() {
        Layer layer = new Layer();
        layer.setName("Layer A");
        assertEquals("Layer A", layer.getName());
    }

    @Test
    void testSetAndGetVisible() {
        Layer layer = new Layer();
        layer.setVisible(true);
        assertTrue(layer.getVisible());
        layer.setVisible(false);
        assertFalse(layer.getVisible());
    }

    @Test
    void testSetAndGetListShape() {
        Layer layer = new Layer();
        ArrayList<Shape> shapes = new ArrayList<>();
        Circle circle = new Circle();
        circle.setId(99);
        shapes.add(circle);

        layer.setListShape(shapes);

        assertSame(shapes, layer.getListShape());
        assertEquals(99, layer.getListShape().get(0).getId());
    }

    @Test
    void testDefaultValues() {
        Layer layer = new Layer();
        assertEquals(0, layer.getId());
        assertNull(layer.getName());
        assertFalse(layer.getVisible());
        assertNull(layer.getListShape());
    }
}

