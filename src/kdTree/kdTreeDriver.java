package kdTree;

import javafx.geometry.Point2D;

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

    }
}
