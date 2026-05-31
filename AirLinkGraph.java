import java.util.*;

public class AirLinkGraph {

    static class Graph {

        int V;
        LinkedList<Integer>[] adj;

        Graph(int v) {

            V = v;

            adj =
                    new LinkedList[V];

            for(int i=0;i<V;i++)
                adj[i] =
                        new LinkedList<>();
        }

        void addRoute(
                int u,
                int v) {

            adj[u].add(v);
            adj[v].add(u);
        }

        void display() {

            System.out.println(
                    "\nGRAPH STRUCTURE\n");

            for(int i=0;i<V;i++) {

                System.out.print(
                        i + " -> ");

                for(int x : adj[i])
                    System.out.print(
                            x + " ");

                System.out.println();
            }
        }

        void bfs(int start) {

            boolean[] visited =
                    new boolean[V];

            Queue<Integer> q =
                    new LinkedList<>();

            q.add(start);

            visited[start] = true;

            int count = 0;

            System.out.println(
                    "\nBFS CONNECTIVITY ANALYSIS");

            System.out.println(
                    "\nTraversal Order:\n");

            while(!q.isEmpty()) {

                int node =
                        q.poll();

                count++;

                System.out.print(
                        node + " ");

                for(int nbr :
                        adj[node]) {

                    if(!visited[nbr]) {

                        visited[nbr]
                                = true;

                        q.add(nbr);
                    }
                }
            }

            System.out.println(
                    "\n\nReachable Airports = "
                            + count);
        }
    }

    public static void main(
            String[] args) {

        Graph g =
                new Graph(5);

        System.out.println(
                " AIRLINK AIRPORT ROUTE NETWORK ");

        System.out.println(
                "AIRPORTS\n");

        System.out.println(
                "0 -> Hyderabad");

        System.out.println(
                "1 -> Delhi");

        System.out.println(
                "2 -> Mumbai");

        System.out.println(
                "3 -> Chennai");

        System.out.println(
                "4 -> Goa");

        System.out.println(
                "\nROUTES ADDED\n");

        System.out.println(
                "HYD <-> DEL");

        System.out.println(
                "HYD <-> MUM");

        System.out.println(
                "DEL <-> CHN");

        System.out.println(
                "MUM <-> GOA");

        g.addRoute(0,1);
        g.addRoute(0,2);
        g.addRoute(1,3);
        g.addRoute(2,4);

        g.display();

        g.bfs(0);

        System.out.println(
                "\nComplexity Analysis");

        System.out.println(
                "\nBFS : O(V + E)");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}