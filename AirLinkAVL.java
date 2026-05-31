import java.util.*;

class FlightNode {
    int flightId;
    FlightNode left, right;
    int height;

    FlightNode(int id) {
        flightId = id;
        height = 1;
    }
}

public class AirLinkAVL {

    static List<String> rotationLog = new ArrayList<>();

    static int height(FlightNode node) {
        return node == null ? 0 : node.height;
    }

    static int getBalance(FlightNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    static FlightNode rightRotate(FlightNode y) {

        FlightNode x = y.left;
        FlightNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        rotationLog.add(
                "RR Rotation at pivot Flight "
                        + y.flightId);

        return x;
    }

    static FlightNode leftRotate(FlightNode x) {

        FlightNode y = x.right;
        FlightNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        rotationLog.add(
                "LL Rotation at pivot Flight "
                        + x.flightId);

        return y;
    }

    static FlightNode insert(FlightNode node, int id) {

        if (node == null)
            return new FlightNode(id);

        if (id < node.flightId)
            node.left = insert(node.left, id);

        else if (id > node.flightId)
            node.right = insert(node.right, id);

        else
            return node;

        node.height =
                1 + Math.max(height(node.left),
                        height(node.right));

        int balance = getBalance(node);

        if (balance > 1 &&
                id < node.left.flightId)
            return rightRotate(node);

        if (balance < -1 &&
                id > node.right.flightId)
            return leftRotate(node);

        if (balance > 1 &&
                id > node.left.flightId) {

            node.left = leftRotate(node.left);
            rotationLog.add(
                    "LR Rotation at pivot Flight "
                            + node.flightId);

            return rightRotate(node);
        }

        if (balance < -1 &&
                id < node.right.flightId) {

            node.right = rightRotate(node.right);
            rotationLog.add(
                    "RL Rotation at pivot Flight "
                            + node.flightId);

            return leftRotate(node);
        }

        return node;
    }

    static void descending(FlightNode root) {

        if (root != null) {

            descending(root.right);

            System.out.println(
                    root.flightId
                            + "(bf="
                            + getBalance(root)
                            + ")");

            descending(root.left);
        }
    }

    static void inorder(FlightNode root) {

        if (root != null) {

            inorder(root.left);

            System.out.printf(
                    "%-10d %-20s%n",
                    root.flightId,
                    route(root.flightId));

            inorder(root.right);
        }
    }

    static String route(int id) {

        switch (id) {

            case 101:
                return "HYD -> DEL";

            case 105:
                return "HYD -> MUM";

            case 103:
                return "DEL -> BLR";

            case 110:
                return "BLR -> CHN";

            case 115:
                return "CHN -> GOA";

            case 120:
                return "GOA -> HYD";

            case 125:
                return "DEL -> KOL";

            default:
                return "Unknown Route";
        }
    }

    static void topK(FlightNode root,
                     List<Integer> result,
                     int k) {

        if (root == null ||
                result.size() >= k)
            return;

        topK(root.right, result, k);

        if (result.size() < k)
            result.add(root.flightId);

        topK(root.left, result, k);
    }

    public static void main(String[] args) {

        int[] flights =
                {101, 105, 103,
                        110, 115,
                        120, 125};

        FlightNode root = null;

        System.out.println(
                " AIRLINK FLIGHT SCHEDULE MANAGEMENT ");

        System.out.println(
                "AVL INSERTION (Flight Arrival Order)\n");

        System.out.print(
                "Flight IDs inserted:\n");

        for (int f : flights) {

            System.out.print(f + " ");

            root = insert(root, f);
        }

        System.out.println("\n");

        System.out.println(
                "Rotations that occurred:\n");

        int count = 1;

        for (String r : rotationLog) {

            System.out.println(
                    count++ + ") " + r);
        }

        System.out.println(
                "FINAL AVL TREE (Descending)");

        descending(root);

        System.out.println(
                "SORTED FLIGHT SCHEDULE");

        System.out.printf(
                "%-10s %-20s%n",
                "FlightID",
                "Route");

        inorder(root);

        List<Integer> top =
                new ArrayList<>();

        topK(root, top, 3);

        System.out.println(
                "TOP 3 RECENT FLIGHTS");

        System.out.println(top);

        System.out.println(
                "\nTime Complexity:");
        System.out.println(
                "Insert : O(log n)");
        System.out.println(
                "Search : O(log n)");
        System.out.println(
                "Top-K  : O(log n + k)");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}