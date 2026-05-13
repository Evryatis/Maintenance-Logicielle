import java.util.ArrayList;

import command.Command;
import command.CommandParser;
import layer.Areas;
import layer.Layer;
import layer.Visualizer;
import shape.Cercle;
import shape.Curve;
import shape.Line;
import shape.Point;
import shape.Polygone;
import shape.Rectangle;
import shape.Squar;

/**
 * Classe principale de l'application Pixel Tracer.
 * Gère la boucle REPL (Read-Eval-Print Loop) et la création des formes.
 */
public class Main {

    public static void main(String[] args) {
        // ========== Initialisation de la structure de l'application ==========
        Areas currentArea = new Areas();
        currentArea.setId(1);
        currentArea.setName("Zone par défaut");
        currentArea.setWidth(50);              // Largeur du canvas
        currentArea.setHeight(20);             // Hauteur du canvas
        currentArea.setEmptyChar('.');         // Caractère vide
        currentArea.setFullChar('#');          // Caractère pour les pixels

        // Créer une couche par défaut
        ArrayList<Layer> layers = new ArrayList<>();
        Layer currentLayer = new Layer();
        currentLayer.setId(1);
        currentLayer.setName("Couche par défaut");
        currentLayer.setVisible(true);
        currentLayer.setListShape(new ArrayList<>());
        layers.add(currentLayer);
        currentArea.setLayers(layers);

        System.out.println("Bienvenue dans Pixel Tracer Java!");
        System.out.println("Tapez 'help' pour les commandes, 'exit' pour quitter.");

        // ========== Boucle principale REPL ==========
        while (true) {
            Command cmd = CommandParser.readCommandFromInput();
            if (cmd == null) {
                continue;
            }

            String cmdName = cmd.getName();

            switch (cmdName) {
                case "exit" -> {
                    System.out.println("Au revoir.");
                    return;
                }
                case "help" -> printHelp();
                case "clear" -> {
                    currentLayer.getListShape().clear();
                    System.out.println("Formes supprimées.");
                }
                case "list" -> {
                    System.out.println("Formes stockées:");
                    for (shape.Shape shape : currentLayer.getListShape()) {
                        System.out.println(shape);
                    }
                }
                case "render", "draw" -> Visualizer.render(currentArea);
                case "point" -> {
                    if (cmd.getIntParams().size() >= 2) {
                        Point p = new Point();
                        p.setPos_x(cmd.getIntParams().get(0));
                        p.setPos_y(cmd.getIntParams().get(1));
                        p.setColor(0);
                        currentLayer.getListShape().add(p);
                        System.out.println("Point ajouté: " + p);
                    } else {
                        System.out.println("Utilisation: point x y");
                    }
                }
                case "line" -> {
                    if (cmd.getIntParams().size() >= 4) {
                        Point p1 = new Point();
                        p1.setPos_x(cmd.getIntParams().get(0));
                        p1.setPos_y(cmd.getIntParams().get(1));
                        Point p2 = new Point();
                        p2.setPos_x(cmd.getIntParams().get(2));
                        p2.setPos_y(cmd.getIntParams().get(3));
                        Line line = new Line();
                        line.setP1(p1);
                        line.setP2(p2);
                        line.setColor(0);
                        currentLayer.getListShape().add(line);
                        System.out.println("Ligne ajoutée: " + line);
                    } else {
                        System.out.println("Utilisation: line x1 y1 x2 y2");
                    }
                }
                case "circle" -> {
                    if (cmd.getIntParams().size() >= 3) {
                        Point center = new Point();
                        center.setPos_x(cmd.getIntParams().get(0));
                        center.setPos_y(cmd.getIntParams().get(1));
                        int radius = cmd.getIntParams().get(2);
                        Cercle cercle = new Cercle();
                        cercle.setCenter(center);
                        cercle.setRadus(radius);
                        cercle.setColor(0);
                        currentLayer.getListShape().add(cercle);
                        System.out.println("Cercle ajouté: " + cercle);
                    } else {
                        System.out.println("Utilisation: circle x y radius");
                    }
                }
                case "rectangle" -> {
                    if (cmd.getIntParams().size() >= 4) {
                        Point p1 = new Point();
                        p1.setPos_x(cmd.getIntParams().get(0));
                        p1.setPos_y(cmd.getIntParams().get(1));
                        Rectangle rectangle = new Rectangle();
                        rectangle.setP1(p1);
                        rectangle.setWidth(cmd.getIntParams().get(2));
                        rectangle.setHeight(cmd.getIntParams().get(3));
                        rectangle.setColor(0);
                        currentLayer.getListShape().add(rectangle);
                        System.out.println("Rectangle ajouté: " + rectangle);
                    } else {
                        System.out.println("Utilisation: rectangle x y width height");
                    }
                }
                case "square" -> {
                    if (cmd.getIntParams().size() >= 3) {
                        Point p1 = new Point();
                        p1.setPos_x(cmd.getIntParams().get(0));
                        p1.setPos_y(cmd.getIntParams().get(1));
                        Squar square = new Squar();
                        square.setP1(p1);
                        square.setLength(cmd.getIntParams().get(2));
                        square.setColor(0);
                        currentLayer.getListShape().add(square);
                        System.out.println("Square ajouté: " + square);
                    } else {
                        System.out.println("Utilisation: square x y length");
                    }
                }
                case "polygone" -> {
                    if (cmd.getIntParams().size() >= 6 && cmd.getIntParams().size() % 2 == 0) {
                        ArrayList<Point> points = new ArrayList<>();
                        for (int i = 0; i < cmd.getIntParams().size(); i += 2) {
                            Point point = new Point();
                            point.setPos_x(cmd.getIntParams().get(i));
                            point.setPos_y(cmd.getIntParams().get(i + 1));
                            points.add(point);
                        }
                        Polygone polygone = new Polygone();
                        polygone.setPoints(points);
                        polygone.setColor(0);
                        currentLayer.getListShape().add(polygone);
                        System.out.println("Polygone ajouté: " + polygone);
                    } else {
                        System.out.println("Utilisation: polygone x1 y1 x2 y2 x3 y3 [...]");
                    }
                }
                case "curve" -> {
                    if (cmd.getIntParams().size() >= 8) {
                        Point start = new Point();
                        start.setPos_x(cmd.getIntParams().get(0));
                        start.setPos_y(cmd.getIntParams().get(1));
                        Point control1 = new Point();
                        control1.setPos_x(cmd.getIntParams().get(2));
                        control1.setPos_y(cmd.getIntParams().get(3));
                        Point control2 = new Point();
                        control2.setPos_x(cmd.getIntParams().get(4));
                        control2.setPos_y(cmd.getIntParams().get(5));
                        Point end = new Point();
                        end.setPos_x(cmd.getIntParams().get(6));
                        end.setPos_y(cmd.getIntParams().get(7));
                        Curve curve = new Curve();
                        curve.setP1(start);
                        curve.setControl1(control1);
                        curve.setControl2(control2);
                        curve.setP2(end);
                        curve.setColor(0);
                        currentLayer.getListShape().add(curve);
                        System.out.println("Curve ajoutée: " + curve);
                    } else {
                        System.out.println("Utilisation: curve x1 y1 c1x c1y c2x c2y x2 y2");
                    }
                }
                default -> System.out.println("Commande inconnue: " + cmdName);
            }
        }
    }

    /**
     * Affiche l'aide avec toutes les commandes disponibles.
     */
    private static void printHelp() {
        System.out.println("Commandes disponibles:");
        System.out.println("  point x y                    - ajouter un point à (x, y)");
        System.out.println("  line x1 y1 x2 y2             - ajouter une ligne de (x1, y1) à (x2, y2)");
        System.out.println("  circle x y radius            - ajouter un cercle de centre (x, y)");
        System.out.println("  rectangle x y width height   - ajouter un rectangle");
        System.out.println("  square x y length            - ajouter un carré");
        System.out.println("  curve x1 y1 c1x c1y c2x c2y x2 y2 - ajouter une courbe de Bézier");
        System.out.println("  polygone x1 y1 x2 y2 ...     - ajouter un polygone fermé");
        System.out.println("  render (ou draw)             - afficher le canvas avec toutes les formes");
        System.out.println("  clear                        - effacer toutes les formes de la couche");
        System.out.println("  list                         - lister toutes les formes de la couche actuelle");
        System.out.println("  help                         - afficher cette aide");
        System.out.println("  exit                         - quitter l'application");
    }
}

