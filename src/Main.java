import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import command.Command;
import command.CommandParser;
import layer.Areas;
import layer.Layer;

public class Main {

    private static final List<String> SHAPE_COMMANDS = Arrays.asList("point", "line", "circle", "square", "rectangle", "polygone");

    public static void main(String[] args) {
        List<Command> shapeRequests = new ArrayList<>();

        // Initialize the app structure
        Areas currentArea = new Areas();
        currentArea.setId(1);
        currentArea.setName("Default Area");
        currentArea.setWidth(100);
        currentArea.setHeight(100);
        currentArea.setEmptyChar('.');
        currentArea.setFullChar('#');

        ArrayList<Layer> layers = new ArrayList<>();
        Layer currentLayer = new Layer();
        currentLayer.setId(1);
        currentLayer.setName("Default Layer");
        currentLayer.setVisible(true);
        layers.add(currentLayer);
        currentArea.setLayers(layers);

        System.out.println("Welcome to Pixel Tracer Java!");
        System.out.println("Type 'help' for commands, 'exit' to quit.");

        while (true) {
            Command cmd = CommandParser.readCommandFromInput();
            if (cmd == null) {
                continue;
            }

            String cmdName = cmd.getName();

            if ("exit".equals(cmdName)) {
                break;
            } else if ("help".equals(cmdName)) {
                printHelp();
            } else if ("clear".equals(cmdName)) {
                shapeRequests.clear();
                System.out.println("Shape requests cleared.");
            } else if ("list".equals(cmdName)) {
                System.out.println("Stored shape requests:");
                for (Command request : shapeRequests) {
                    System.out.println(request);
                }
            } else if (SHAPE_COMMANDS.contains(cmdName)) {
                // Store the shape request
                shapeRequests.add(cmd);
                System.out.println("Shape request stored: " + cmd);
            } else {
                System.out.println("Unknown command: " + cmdName);
            }
        }

        System.out.println("Exiting. Total shape requests stored: " + shapeRequests.size());
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  point x y");
        System.out.println("  line x1 y1 x2 y2");
        System.out.println("  circle x y radius");
        System.out.println("  square x y size");
        System.out.println("  rectangle x y width height");
        System.out.println("  polygone x1 y1 x2 y2 ...");
        System.out.println("  clear - clear all stored requests");
        System.out.println("  list - list all stored requests");
        System.out.println("  help - show this help");
        System.out.println("  exit - exit the application");
    }
}
