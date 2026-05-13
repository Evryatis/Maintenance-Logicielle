package command;

import java.util.Scanner;

/**
 * Classe responsable du parsing des commandes utilisateur.
 * Transforme une chaîne d'entrée en objet Command typé.
 */
public class CommandParser {

    /**
     * Parse une chaîne d'entrée en objet Command.
     * Identifie le nom de la commande et ses paramètres.
     * Les paramètres sont automatiquement typés (int, float, string).
     * 
     * @param input chaîne à parser (ex: "line 5 2 20 10")
     * @return objet Command contenant le nom et les paramètres typés, ou null si vide
     */
    public static Command parseCommand(String input) {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        
        Command cmd = new Command();
        String[] tokens = trimmed.split("\\s+");
        
        // Le premier token est le nom de la commande
        cmd.setName(tokens[0].toLowerCase());
        
        // Les tokens suivants sont les paramètres
        // On teste chaque paramètre pour déterminer son type
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            try {
                // Essayer comme entier d'abord
                int intVal = Integer.parseInt(token);
                cmd.addIntParam(intVal);
            } catch (NumberFormatException e) {
                try {
                    // Essayer comme décimal
                    float floatVal = Float.parseFloat(token);
                    cmd.addFloatParam(floatVal);
                } catch (NumberFormatException e2) {
                    // Sinon, traiter comme chaîne de caractères
                    cmd.addStrParam(token);
                }
            }
        }
        return cmd;
    }

    /**
     * Lit une commande depuis l'entrée standard.
     * Affiche l'invite "~> " et attend la saisie de l'utilisateur.
     * 
     * @return objet Command parsé depuis l'input utilisateur
     */
    public static Command readCommandFromInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("~> ");
        String input = scanner.nextLine();
        return parseCommand(input);
    }
}