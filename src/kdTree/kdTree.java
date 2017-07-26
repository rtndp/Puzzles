package kdTree;

import javafx.geometry.Point2D;

public class kdTree {

    //k is the no of dimensions
    final int k = 2;

    class Node {
        private Point2D point;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(Point2D point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }

        public Node(Point2D point, Node left, Node right) {
            this.point = point;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    private Node insertHelper(Node root, Point2D point, int depth) {
        if (root == null) {
            return new Node(point);
        }

        int currentDimension = depth % k;

        //If currentDimension is 0, x-axis, else, y-axis
        if (currentDimension == 0) {
            if (point.getX() < root.point.getX()) {
                root.left = insertHelper(root.left, point, depth + 1);
            } else {
                root.right = insertHelper(root.right, point, depth + 1);
            }

        } else if (currentDimension == 1) {
            if (point.getY() < root.point.getY()) {
                root.left = insertHelper(root.left, point, depth + 1);
            } else {
                root.right = insertHelper(root.right, point, depth + 1);
            }
        } else {
            //
        }
        return root;
    }

    public void insert(Point2D point) {
        root = insertHelper(root, point, 0);
    }
}
