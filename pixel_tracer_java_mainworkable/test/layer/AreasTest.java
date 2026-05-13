package layer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class AreasTest {

    @Test
    void testSetAndGetLayers() {
        Areas areas = new Areas();
        ArrayList<Layer> layers = new ArrayList<>();
        Layer layer = new Layer();
        layer.setId(1);
        layer.setName("Layer 1");
        layers.add(layer);

        areas.setLayers(layers);

        assertSame(layers, areas.getLayers());
        assertEquals(1, areas.getLayers().get(0).getId());
        assertEquals("Layer 1", areas.getLayers().get(0).getName());
    }

    @Test
    void testSetAndGetMetadataFields() {
        Areas areas = new Areas();

        areas.setId(42);
        areas.setName("Test Area");
        areas.setWidth(800);
        areas.setHeight(600);
        areas.setEmptyChar('.');
        areas.setFullChar('X');

        assertEquals(42, areas.getId());
        assertEquals("Test Area", areas.getName());
        assertEquals(800, areas.getWidth());
        assertEquals(600, areas.getHeight());
        assertEquals('.', areas.getEmptyChar());
        assertEquals('X', areas.getFullChar());
    }

    @Test
    void testDefaultValuesAreNullOrZeroChar() {
        Areas areas = new Areas();

        assertNull(areas.getLayers());
        assertNull(areas.getId());
        assertNull(areas.getName());
        assertNull(areas.getWidth());
        assertNull(areas.getHeight());
        assertEquals('\u0000', areas.getEmptyChar());
        assertEquals('\u0000', areas.getFullChar());
    }
}

