package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    void testSetEtGetName() {
        Command cmd = new Command();
        cmd.setName("test");
        assertEquals("test", cmd.getName());
    }

    @Test
    void testNameParDefautEstNull() {
        Command cmd = new Command();
        assertNull(cmd.getName());
    }

    @Test
    void testAddEtGetIntParams() {
        Command cmd = new Command();
        cmd.addIntParam(5);
        cmd.addIntParam(10);
        assertEquals(2, cmd.getIntParams().size());
        assertEquals(5, cmd.getIntParams().get(0));
        assertEquals(10, cmd.getIntParams().get(1));
    }

    @Test
    void testAddEtGetStrParams() {
        Command cmd = new Command();
        cmd.addStrParam("hello");
        cmd.addStrParam("world");
        assertEquals(2, cmd.getStrParams().size());
        assertEquals("hello", cmd.getStrParams().get(0));
        assertEquals("world", cmd.getStrParams().get(1));
    }

    @Test
    void testAddEtGetFloatParams() {
        Command cmd = new Command();
        cmd.addFloatParam(3.14f);
        cmd.addFloatParam(2.71f);
        assertEquals(2, cmd.getFloatParams().size());
        assertEquals(3.14f, cmd.getFloatParams().get(0));
        assertEquals(2.71f, cmd.getFloatParams().get(1));
    }

    @Test
    void testToString() {
        Command cmd = new Command();
        cmd.setName("circle");
        cmd.addIntParam(10);
        cmd.addStrParam("red");
        cmd.addFloatParam(5.5f);
        String expected = "Command{name='circle', intParams=[10], strParams=[red], floatParams=[5.5]}";
        assertEquals(expected, cmd.toString());
    }
}