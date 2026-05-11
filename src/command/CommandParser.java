package command;

import java.util.Scanner;

public class CommandParser {

    public static Command parseCommand(String input) {
        Command cmd = new Command();
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length == 0) {
            return null;
        }
        cmd.setName(tokens[0].toLowerCase());
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            try {
                int intVal = Integer.parseInt(token);
                cmd.addIntParam(intVal);
            } catch (NumberFormatException e) {
                try {
                    float floatVal = Float.parseFloat(token);
                    cmd.addFloatParam(floatVal);
                } catch (NumberFormatException e2) {
                    cmd.addStrParam(token);
                }
            }
        }
        return cmd;
    }

    public static Command readCommandFromInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("~> ");
        String input = scanner.nextLine();
        return parseCommand(input);
    }
}