# Pixel Tracer Java

## Vue d'ensemble

**Pixel Tracer** est une application de dessin en ligne de commande qui utilise l'algorithme de Bresenham pour afficher des formes géométriques (lignes, points) sur un canvas ASCII. L'application suit une architecture modulaire avec séparation des responsabilités entre les formes, le rendu, les couches et l'interface utilisateur.

---

## Architecture générale

L'application est organisée en 4 packages principaux :

```
┌─────────────────────────────────────────────────────┐
│                    Main (Interface CLI)             │
│          (Lecture des commandes utilisateur)        │
└──────────────────┬──────────────────────────────────┘
                   │
        ┌──────────┴──────────┬────────────────┐
        │                     │                │
   ┌────▼────┐          ┌────▼───┐     ┌─────▼──────┐
   │ command  │          │ shape  │     │    layer   │
   │ Parser   │          │        │     │            │
   └────┬────┘          └────┬───┘     └─────┬──────┘
        │                    │              │
        │             ┌──────▼──────┐      │
        │             │ Visualizer  │◄─────┘
        │             └──────┬──────┘
        │                    │
        │             ┌──────▼──────────┐
        │             │ BresenhamRasterizer
        │             │ (Algoritme pixel)│
        │             └──────┬──────────┘
        │                    │
        └────────────────┬───┴────────────┐
                         │                │
                    ┌────▼────┐    ┌─────▼──┐
                    │  Pixel  │    │ Console│
                    └─────────┘    └────────┘
```

---

## Description des packages

### 1. **command** - Gestion des commandes utilisateur
**Fichiers :** `Command.java`, `CommandParser.java`

- **`Command.java`** : Modèle de données pour stocker une commande avec :
  - `name` : nom de la commande (ex: "line", "point")
  - `intParams`, `strParams`, `floatParams` : listes de paramètres

- **`CommandParser.java`** : Parser qui transforme l'entrée utilisateur en objet `Command`
  - Lit l'input via `Scanner`
  - Sépare le nom et les paramètres
  - Clarifie les types (int, float, string)

**Flux :** Utilisateur → `CommandParser.readCommandFromInput()` → `Command`

---

### 2. **shape** - Définition des formes géométriques
**Fichiers :** `Shape.java` (classe abstraite), `Point.java`, `Line.java`, `Cercle.java`, `Polygone.java`, `Rectangle.java`, `Squar.java`

- **`Shape.java`** : Classe abstraite mère avec propriétés communes :
  - `id`, `shapeType`, `fill`, `thickness`, `color`, `rotation`

- **`Point.java`** : Simple point avec `pos_x`, `pos_y`

- **`Line.java`** : Ligne avec deux points `p1` et `p2`

- **`Cercle.java`** : Cercle avec `center` et `radius`

- Autres formes : `Rectangle`, `Squar`, `Polygone`

**Flux :** Objets Shape créés dans `Main` → Stockés dans `Layer` → Utilisés par `Visualizer`

---

### 3. **pixel** - Rasterization et pixel
**Fichiers :** `Pixel.java`, `Rasterizer.java`, `BresenhamRasterizer.java`

- **`Pixel.java`** : Record immutable `(x, y, color)` représentant un pixel

- **`Rasterizer.java`** : Interface définissant le contrat pour convertir une forme en pixels
  ```java
  List<Pixel> rasterize(Shape shape);
  ```

- **`BresenhamRasterizer.java`** : Implémentation concrète utilisant l'algorithme de Bresenham
  - Traite les `Line` avec l'algorithme classique de Bresenham
  - Retourne une liste de pixels qui forment la ligne

**Flux :** `Shape` → `BresenhamRasterizer.rasterize()` → `List<Pixel>`

---

### 4. **layer** - Gestion des couches et rendu
**Fichiers :** `Areas.java`, `Layer.java`, `Visualizer.java`

- **`Areas.java`** : Canvas/Zone principale avec properties :
  - `width`, `height` : dimensions du canvas
  - `emptyChar`, `fullChar` : caractères d'affichage ('.', '#')
  - `layers` : liste de couches

- **`Layer.java`** : Couche de dessin contenant :
  - `id`, `name`, `visible` : métadonnées
  - `listShape` : liste des formes sur cette couche

- **`Visualizer.java`** : Classe responsable du rendu final
  1. Crée une grille 2D `char[][]` de la taille du canvas
  2. Initialise avec `emptyChar` ('.')
  3. Pour chaque couche visible, pour chaque forme :
     - Appelle `BresenhamRasterizer.rasterize(shape)` pour obtenir les pixels
     - Place chaque pixel sur la grille avec `fullChar` ('#')
  4. Affiche la grille sur la console

**Flux :** `Areas` → `Visualizer.render()` → Grille → Console

---

## Déroulement complet d'une commande

### Exemple : `line 5 2 20 10`

```
┌─────────────────────────────────────────────────────────────┐
│ 1. Utilisateur tape: "line 5 2 20 10"                       │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
        ┌─────────────────────────────┐
        │ CommandParser.parseCommand  │
        │ Input: "line 5 2 20 10"     │
        │ Output: Command {           │
        │   name: "line"              │
        │   intParams: [5, 2, 20, 10] │
        │ }                           │
        └──────────────┬──────────────┘
                       │
                       ▼
        ┌─────────────────────────────┐
        │ Main.java                   │
        │ Crée 2 Points et 1 Line     │
        │ Les ajoute à currentLayer   │
        └──────────────┬──────────────┘
                       │
                       ▼
        ┌─────────────────────────────┐
        │ Utilisateur tape: "render"  │
        └──────────────┬──────────────┘
                       │
                       ▼
        ┌─────────────────────────────────────┐
        │ Visualizer.render(currentArea)      │
        │ - Créer grille 50x20 remplie de '.'│
        │ - Pour chaque couche visible...      │
        └──────────────┬──────────────────────┘
                       │
                       ▼
        ┌─────────────────────────────────────┐
        │ BresenhamRasterizer.rasterize(line) │
        │ Retourne List<Pixel> avec tous      │
        │ les pixels entre (5,2) et (20,10)   │
        └──────────────┬──────────────────────┘
                       │
                       ▼
        ┌─────────────────────────────────────┐
        │ Visualizer place les pixels          │
        │ grid[y][x] = '#'                    │
        └──────────────┬──────────────────────┘
                       │
                       ▼
        ┌─────────────────────────────────────┐
        │ Affichage sur console:              │
        │ #..................................  │
        │ .#.................................  │
        │ ..#............................... │
        │ ... (ligne tracée)                   │
        └─────────────────────────────────────┘
```

---

## Appels entre les fichiers

### Main.java
↓ crée des objets → `Point`, `Line`, `Layer`, `Areas`
↓ appelle → `CommandParser.readCommandFromInput()`
↓ appelle → `Visualizer.render()`

### Visualizer.java
↓ utilise → `BresenhamRasterizer` (via interface `Rasterizer`)
↓ accède à → `Layer.getListShape()`
↓ accède à → `Areas.getWidth()`, `.getHeight()`, `.getEmptyChar()`, `.getFullChar()`

### BresenhamRasterizer.java
↓ traite → `Shape` (interface/héritage)
↓ retourne → `List<Pixel>`

### Layer.java
↓ contient → `List<Shape>`

### Areas.java
↓ contient → `List<Layer>`

---

## Guide d'utilisation

### Commandes disponibles

| Commande | Format | Exemple |
|----------|--------|---------|
| Créer un point | `point x y` | `point 5 3` |
| Créer une ligne | `line x1 y1 x2 y2` | `line 5 2 20 10` |
| Afficher le canvas | `render` ou `draw` | `render` |
| Lister les formes | `list` | `list` |
| Effacer les formes | `clear` | `clear` |
| Aide | `help` | `help` |
| Quitter | `exit` | `exit` |

### Exemple d'utilisation

```
~> line 10 5 40 15
Line added: Line[p1=Point[pos_x=10, pos_y=5], p2=Point[pos_x=40, pos_y=15]]

~> line 15 2 15 18
Line added: Line[p1=Point[pos_x=15, pos_y=2], p2=Point[pos_x=15, pos_y=18]]

~> render

..................................................
.....#...........................................
.....#...........................................
.....#...........................................
.....#.......#............................#.....
.....#.......#...........................#......
.....#.......#..........................#.......
.....#.......#.........................#........
.......#.....#........................#.........
.........#...#.......................#..........
...........#.#......................#...........
.............#.....................#............
.............#....................#.............
.............#...................#..............
.............#..................#...............
.............#.................#................
.............#...............#..................

```

---

## Prérequis

- Java 21
- Maven 3.x

## Installation et lancement

```bash
# Compiler
mvn clean compile

# Lancer le programme
mvn compile exec:java@run

# Lancer les tests
mvn test
```

---

## Structure des fichiers

```
src/
├── Main.java                          # Point d'entrée, interface CLI
├── command/
│   ├── Command.java                   # Modèle de commande
│   └── CommandParser.java             # Parser d'input
├── shape/
│   ├── Shape.java                     # Classe abstraite
│   ├── Point.java                     # Forme: Point
│   ├── Line.java                      # Forme: Ligne
│   ├── Cercle.java                    # Forme: Cercle
│   ├── Polygone.java                  # Forme: Polygone
│   ├── Rectangle.java                 # Forme: Rectangle
│   └── Squar.java                     # Forme: Carré
├── pixel/
│   ├── Pixel.java                     # Record pixel (x, y, color)
│   ├── Rasterizer.java                # Interface de rasterization
│   └── BresenhamRasterizer.java       # Impl. Bresenham
└── layer/
    ├── Areas.java                     # Canvas principal
    ├── Layer.java                     # Couche de dessin
    └── Visualizer.java                # Rendu sur console
```

---

## Notes techniques

- **Algorithme Bresenham** : L'algorithme de rasterization utilisé pour tracer les lignes de manière optimisée sans utiliser de multiplication/division coûteuses
- **Grille 2D** : Le rendu utilise une grille `char[][]` pour maximiser la performance
- **Couches visibles** : Seules les couches avec `visible=true` sont affichées
- **Limites du canvas** : Les pixels en dehors des limites sont ignorés (clipping)

---
