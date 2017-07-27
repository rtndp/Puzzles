package kdTree;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

import java.util.List;

public class kdTreeDriver {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        kdTree kd = new kdTree();

        kd.insert(new Point2D(3, 6));
        kd.insert(new Point2D(17, 15));
        kd.insert(new Point2D(13, 15));
        kd.insert(new Point2D(6, 12));
        kd.insert(new Point2D(9, 1));
        kd.insert(new Point2D(2, 7));
        kd.insert(new Point2D(10, 19));

        System.out.println(kd.size());

        BoundingBox bbox = new BoundingBox(1, 1, 2, 5);
        System.out.println(bbox.getMaxX() + " " + bbox.getMaxY());

        List<Point2D> found = kd.rangeLookup(new BoundingBox(1, 1, 2, 5));
        System.out.println(found.toString());
    }
}
