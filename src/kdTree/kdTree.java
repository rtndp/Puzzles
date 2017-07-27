package kdTree;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class kdTree {

    public static final Comparator<Point2D> X_ORDER = new XOrder();

    private static class XOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.getX() < q.getX()) return -1;
            if (p.getX() > q.getX()) return +1;
            return 0;
        }
    }

    public static final Comparator<Point2D> Y_ORDER = new YOrder();

    private static class YOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.getY() < q.getY()) return -1;
            if (p.getY() > q.getY()) return +1;
            return 0;
        }
    }

    class Node {
        private Point2D point;
        private Node left;
        private Node right;
        private boolean vertical;

        public Node(Point2D point, Node left, Node right, boolean vertical) {
            this.point = point;

            this.left = left;
            this.right = right;

            //Vertical is true when the split is along the Y axis
            this.vertical = vertical;
        }

        public Point2D getPoint() {
            return point;
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean isVertical() {
            return vertical;
        }

        public void setVertical(boolean vertical) {
            this.vertical = vertical;
        }
    }

    private Node root;
    private int n;

    public kdTree() {
        this.root = null;
        this.n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private Node insertHelper(Node node, Point2D point, boolean vertical) {
        if (node == null) {
            n = n + 1;
            return new Node(point, null, null, vertical);
        }

        Point2D nodePoint = node.getPoint();

        if (nodePoint.equals(point)) {
            return node;
        }

        Comparator<Point2D> comparator = X_ORDER;

        if (vertical) {
            comparator = Y_ORDER;
        }

        int comparatorResult = comparator.compare(point, nodePoint);

        if (comparatorResult < 0) {
            node.setLeft(insertHelper(node.getLeft(), point, !vertical));
        } else {
            node.setRight(insertHelper(node.getRight(), point, !vertical));
        }

        return node;
    }

    public void insert(Point2D point) {
        root = insertHelper(root, point, true);
    }

    public List<Point2D> rangeLookup(BoundingBox boundingBox) {
        List<Point2D> selected = new ArrayList<Point2D>();
        lookupHelper(root, boundingBox, selected);
        return selected;
    }

    private void lookupHelper(Node node, BoundingBox boundingBox, List<Point2D> selected) {

        if (node == null) {
            return;
        }

        Point2D currPoint = node.getPoint();

        if (boundingBox.contains(currPoint)) {
            selected.add(currPoint);
        }

        double pointCoordinate = node.getPoint().getY();
        double minCoordinate = boundingBox.getMinY();
        double maxCoordinate = boundingBox.getMaxY();

        if (node.isVertical()) {
            pointCoordinate = node.getPoint().getX();
            minCoordinate = boundingBox.getMinX();
            maxCoordinate = boundingBox.getMaxY();
        }

        if (minCoordinate < pointCoordinate) {
            lookupHelper(node.getLeft(), boundingBox, selected);
        }

        if (maxCoordinate >= pointCoordinate) {
            lookupHelper(node.getRight(), boundingBox, selected);
        }

    }

}
