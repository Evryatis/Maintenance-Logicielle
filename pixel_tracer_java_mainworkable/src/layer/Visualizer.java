package layer;

import java.util.List;

import pixel.BresenhamRasterizer;
import pixel.Pixel;
import pixel.Rasterizer;
import shape.Shape;

/**
 * Classe responsable du rendu visuel des formes sur la console.
 * Elle convertit les pixels rasterisés en une grille 2D affichable.
 */
public class Visualizer {
    
    /**
     * Affiche le contenu de la zone (canvas) avec toutes les formes des couches visibles.
     * @param area la zone à afficher
     */
    public static void render(Areas area) {
        if (area == null || area.getLayers() == null) {
            System.out.println("Aucune zone ou couche à afficher.");
            return;
        }
        
        int width = area.getWidth();
        int height = area.getHeight();
        char emptyChar = area.getEmptyChar();
        char fullChar = area.getFullChar();
        
        // Création d'une grille 2D qui représente le canvas
        char[][] grid = new char[height][width];
        
        // Initialisation avec le caractère vide
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = emptyChar;
            }
        }
        
        // Rasterization et rendu de toutes les formes des couches visibles
        Rasterizer rasterizer = new BresenhamRasterizer();
        
        for (Layer layer : area.getLayers()) {
            // Sauter les couches invisibles
            if (!layer.getVisible()) {
                continue;
            }
            
            if (layer.getListShape() == null) {
                continue;
            }
            
            // Traiter chaque forme de la couche
            for (Shape shape : layer.getListShape()) {
                // Convertir la forme en pixels via l'algorithme de Bresenham
                List<Pixel> pixels = rasterizer.rasterize(shape);
                
                // Placer les pixels sur la grille
                for (Pixel pixel : pixels) {
                    int x = pixel.x();
                    int y = pixel.y();
                    
                    // Vérifier les limites du canvas
                    if (x >= 0 && x < width && y >= 0 && y < height) {
                        grid[y][x] = fullChar;
                    }
                }
            }
        }
        
        // Afficher la grille sur la console
        System.out.println();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
