package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CommandParserTest {

    @Test
    void testParseCommandVide() {
        Command cmd = CommandParser.parseCommand("");
        assertNull(cmd);
    }

    @Test
    void testParseCommandJustName() {
        Command cmd = CommandParser.parseCommand("circle");
        assertNotNull(cmd);
        assertEquals("circle", cmd.getName());
        assertTrue(cmd.getIntParams().isEmpty());
        assertTrue(cmd.getStrParams().isEmpty());
        assertTrue(cmd.getFloatParams().isEmpty());
    }

    @Test
    void testParseCommandWithInts() {
        Command cmd = CommandParser.parseCommand("move 10 20");
        assertNotNull(cmd);
        assertEquals("move", cmd.getName());
        assertEquals(2, cmd.getIntParams().size());
        assertEquals(10, cmd.getIntParams().get(0));
        assertEquals(20, cmd.getIntParams().get(1));
        assertTrue(cmd.getStrParams().isEmpty());
        assertTrue(cmd.getFloatParams().isEmpty());
    }

    @Test
    void testParseCommandWithFloats() {
        Command cmd = CommandParser.parseCommand("scale 1.5 2.0");
        assertNotNull(cmd);
        assertEquals("scale", cmd.getName());
        assertEquals(2, cmd.getFloatParams().size());
        assertEquals(1.5f, cmd.getFloatParams().get(0));
        assertEquals(2.0f, cmd.getFloatParams().get(1));
        assertTrue(cmd.getIntParams().isEmpty());
        assertTrue(cmd.getStrParams().isEmpty());
    }

    @Test
    void testParseCommandWithStrings() {
        Command cmd = CommandParser.parseCommand("color red blue");
        assertNotNull(cmd);
        assertEquals("color", cmd.getName());
        assertEquals(2, cmd.getStrParams().size());
        assertEquals("red", cmd.getStrParams().get(0));
        assertEquals("blue", cmd.getStrParams().get(1));
        assertTrue(cmd.getIntParams().isEmpty());
        assertTrue(cmd.getFloatParams().isEmpty());
    }

    @Test
    void testParseCommandMixed() {
        Command cmd = CommandParser.parseCommand("draw 5 red 3.14");
        assertNotNull(cmd);
        assertEquals("draw", cmd.getName());
        assertEquals(1, cmd.getIntParams().size());
        assertEquals(5, cmd.getIntParams().get(0));
        assertEquals(1, cmd.getStrParams().size());
        assertEquals("red", cmd.getStrParams().get(0));
        assertEquals(1, cmd.getFloatParams().size());
        assertEquals(3.14f, cmd.getFloatParams().get(0));
    }

    @Test
    void testParseCommandNameLowerCase() {
        Command cmd = CommandParser.parseCommand("CIRCLE");
        assertNotNull(cmd);
        assertEquals("circle", cmd.getName());
    }
}