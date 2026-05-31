import java.util.*;

public class AirLinkFenwick {

    static int[] bit;
    static int n;

    static void update(int index, int value) {

        while(index <= n) {

            bit[index] += value;
            index += index & (-index);
        }
    }

    static int query(int index) {

        int sum = 0;

        while(index > 0) {

            sum += bit[index];
            index -= index & (-index);
        }

        return sum;
    }

    public static void main(String[] args) {

        int[] passengers =
                {120,200,150,180,220};

        n = passengers.length;

        bit = new int[n+1];

        System.out.println(
                " AIRLINK PASSENGER ANALYTICS SYSTEM ");

        System.out.println(
                "PASSENGER ARRIVAL DATA\n");

        for(int i=0;i<n;i++) {

            System.out.println(
                    "Interval "
                            +(i+1)
                            +" : "
                            +passengers[i]
                            +" Passengers");
        }

        System.out.println(
                "\nFENWICK TREE CONSTRUCTION\n");

        for(int i=0;i<n;i++) {

            System.out.println(
                    "Update("
                            +(i+1)
                            +","
                            +passengers[i]
                            +")");

            update(i+1,passengers[i]);
        }

        System.out.println(
                "\nFENWICK TREE ARRAY\n");

        System.out.println(
                Arrays.toString(bit));

        System.out.println(
                "\nPASSENGER ANALYTICS\n");

        for(int i=1;i<=n;i++) {

            System.out.println(
                    "Passengers till Interval "
                            +i
                            +" = "
                            +query(i));
        }

        System.out.println(
                "\nTOP TRAFFIC INTERVAL");

        int max = passengers[0];
        int pos = 1;

        for(int i=1;i<n;i++) {

            if(passengers[i] > max) {

                max = passengers[i];
                pos = i+1;
            }
        }

        System.out.println(
                "\nInterval "
                        +pos
                        +" = "
                        +max
                        +" Passengers");

        System.out.println(
                "\nComplexity Analysis");

        System.out.println(
                "\nUpdate : O(log n)");

        System.out.println(
                "Query : O(log n)");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}