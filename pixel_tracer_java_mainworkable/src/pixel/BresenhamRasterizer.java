package pixel;

import java.util.ArrayList;
import java.util.List;

import shape.Cercle;
import shape.Circle;
import shape.Curve;
import shape.Line;
import shape.Point;
import shape.Polygone;
import shape.Rectangle;
import shape.Shape;
import shape.Squar;

/**
 * Implémentation de l'algorithme de Bresenham pour la rasterization de formes.
 * Convertit les formes géométriques en une liste de pixels.
 */
public class BresenhamRasterizer implements Rasterizer {

    /**
     * Rasterise une forme en liste de pixels.
     * @param shape la forme à rasteriser
     * @return liste de pixels constituant la forme
     */
    @Override
    public List<Pixel> rasterize(Shape shape) {
        List<Pixel> pixels = new ArrayList<>();
        
        if (shape instanceof Point) {
            pixels.addAll(rasterizePoint((Point) shape));
        } else if (shape instanceof Line) {
            pixels.addAll(rasterizeLine((Line) shape));
        } else if (shape instanceof Cercle) {
            pixels.addAll(rasterizeCercle((Cercle) shape));
        } else if (shape instanceof Rectangle) {
            pixels.addAll(rasterizeRectangle((Rectangle) shape));
        } else if (shape instanceof Squar) {
            pixels.addAll(rasterizeSquare((Squar) shape));
        } else if (shape instanceof Polygone) {
            pixels.addAll(rasterizePolygone((Polygone) shape));
        } else if (shape instanceof Circle) {
            pixels.addAll(rasterizeCircle((Circle) shape));
        } else if (shape instanceof Curve) {
            pixels.addAll(rasterizeCurve((Curve) shape));
        }
        
        return pixels;
    }

    private List<Pixel> rasterizePoint(Point point) {
        List<Pixel> pixels = new ArrayList<>();
        pixels.add(new Pixel(point.getPos_x(), point.getPos_y(), point.getColor()));
        return pixels;
    }

    /**
     * Rasterise une ligne en utilisant l'algorithme de Bresenham.
     * Cet algorithme est optimal car il n'utilise que des opérations entières.
     * 
     * @param line la ligne à rasteriser
     * @return liste de pixels formant la ligne
     */
    private List<Pixel> rasterizeLine(Line line) {
        List<Pixel> pixels = new ArrayList<>();
        Point p1 = line.getP1();
        Point p2 = line.getP2();
        
        int x0 = p1.getPos_x();
        int y0 = p1.getPos_y();
        int x1 = p2.getPos_x();
        int y1 = p2.getPos_y();
        
        int color = line.getColor();
        
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        
        int x = x0;
        int y = y0;
        
        while (true) {
            pixels.add(new Pixel(x, y, color));
            if (x == x1 && y == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
        
        return pixels;
    }

    private List<Pixel> rasterizeCercle(Cercle cercle) {
        List<Pixel> pixels = new ArrayList<>();
        if (cercle.getCenter() == null) {
            return pixels;
        }

        Point center = cercle.getCenter();
        int cx = center.getPos_x();
        int cy = center.getPos_y();
        int radius = cercle.getRadus();
        int color = cercle.getColor();

        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            pixels.add(new Pixel(cx + x, cy + y, color));
            pixels.add(new Pixel(cx - x, cy + y, color));
            pixels.add(new Pixel(cx + x, cy - y, color));
            pixels.add(new Pixel(cx - x, cy - y, color));
            pixels.add(new Pixel(cx + y, cy + x, color));
            pixels.add(new Pixel(cx - y, cy + x, color));
            pixels.add(new Pixel(cx + y, cy - x, color));
            pixels.add(new Pixel(cx - y, cy - x, color));

            if (d < 0) {
                d += 4 * x + 6;
            } else {
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }

        return pixels;
    }

    private List<Pixel> rasterizeCircle(Circle circle) {
        List<Pixel> pixels = new ArrayList<>();
        if (circle.getCenter() == null) {
            return pixels;
        }
        Point center = circle.getCenter();
        pixels.add(new Pixel(center.getPos_x(), center.getPos_y(), circle.getColor()));
        return pixels;
    }

    private List<Pixel> rasterizeRectangle(Rectangle rect) {
        List<Pixel> pixels = new ArrayList<>();
        if (rect.getP1() == null) {
            return pixels;
        }

        Point p1 = rect.getP1();
        int x = p1.getPos_x();
        int y = p1.getPos_y();
        int width = rect.getWidth();
        int height = rect.getHeight();
        int color = rect.getColor();

        Line top = new Line();
        Point topRight = new Point(); topRight.setPos_x(x + width - 1); topRight.setPos_y(y);
        top.setP1(p1); top.setP2(topRight); top.setColor(color);
        pixels.addAll(rasterizeLine(top));

        Line right = new Line();
        Point bottomRight = new Point(); bottomRight.setPos_x(x + width - 1); bottomRight.setPos_y(y + height - 1);
        right.setP1(topRight); right.setP2(bottomRight); right.setColor(color);
        pixels.addAll(rasterizeLine(right));

        Line bottom = new Line();
        Point bottomLeft = new Point(); bottomLeft.setPos_x(x); bottomLeft.setPos_y(y + height - 1);
        bottom.setP1(bottomRight); bottom.setP2(bottomLeft); bottom.setColor(color);
        pixels.addAll(rasterizeLine(bottom));

        Line left = new Line();
        left.setP1(bottomLeft); left.setP2(p1); left.setColor(color);
        pixels.addAll(rasterizeLine(left));

        return pixels;
    }

    private List<Pixel> rasterizeSquare(Squar square) {
        List<Pixel> pixels = new ArrayList<>();
        if (square.getP1() == null) {
            return pixels;
        }

        Point p1 = square.getP1();
        int x = p1.getPos_x();
        int y = p1.getPos_y();
        int length = square.getLength();
        int color = square.getColor();

        Line top = new Line();
        Point topRight = new Point(); topRight.setPos_x(x + length - 1); topRight.setPos_y(y);
        top.setP1(p1); top.setP2(topRight); top.setColor(color);
        pixels.addAll(rasterizeLine(top));

        Line right = new Line();
        Point bottomRight = new Point(); bottomRight.setPos_x(x + length - 1); bottomRight.setPos_y(y + length - 1);
        right.setP1(topRight); right.setP2(bottomRight); right.setColor(color);
        pixels.addAll(rasterizeLine(right));

        Line bottom = new Line();
        Point bottomLeft = new Point(); bottomLeft.setPos_x(x); bottomLeft.setPos_y(y + length - 1);
        bottom.setP1(bottomRight); bottom.setP2(bottomLeft); bottom.setColor(color);
        pixels.addAll(rasterizeLine(bottom));

        Line left = new Line();
        left.setP1(bottomLeft); left.setP2(p1); left.setColor(color);
        pixels.addAll(rasterizeLine(left));

        return pixels;
    }

    private List<Pixel> rasterizePolygone(Polygone polygone) {
        List<Pixel> pixels = new ArrayList<>();
        if (polygone.getPoints() == null || polygone.getPoints().size() < 2) {
            return pixels;
        }

        List<Point> points = polygone.getPoints();
        int color = polygone.getColor();
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % points.size());
            Line edge = new Line();
            edge.setP1(a);
            edge.setP2(b);
            edge.setColor(color);
            pixels.addAll(rasterizeLine(edge));
        }

        return pixels;
    }

    private List<Pixel> rasterizeCurve(Curve curve) {
        List<Pixel> pixels = new ArrayList<>();
        if (curve.getP1() == null || curve.getP2() == null || curve.getControl1() == null || curve.getControl2() == null) {
            return pixels;
        }

        Point start = curve.getP1();
        Point control1 = curve.getControl1();
        Point control2 = curve.getControl2();
        Point end = curve.getP2();
        int color = curve.getColor();

        int steps = 24;
        Point previous = start;
        for (int i = 1; i <= steps; i++) {
            double t = i / (double) steps;
            double u = 1 - t;
            double x = u * u * u * start.getPos_x()
                    + 3 * u * u * t * control1.getPos_x()
                    + 3 * u * t * t * control2.getPos_x()
                    + t * t * t * end.getPos_x();
            double y = u * u * u * start.getPos_y()
                    + 3 * u * u * t * control1.getPos_y()
                    + 3 * u * t * t * control2.getPos_y()
                    + t * t * t * end.getPos_y();
            Point current = new Point();
            current.setPos_x((int) Math.round(x));
            current.setPos_y((int) Math.round(y));

            Line segment = new Line();
            segment.setP1(previous);
            segment.setP2(current);
            segment.setColor(color);
            pixels.addAll(rasterizeLine(segment));
            previous = current;
        }

        return pixels;
    }
}
