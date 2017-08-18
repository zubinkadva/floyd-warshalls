
import java.util.Random;
import java.util.Scanner;


/* Author: Zubin Kadva
 * Class: Analysis of Algorithms, Spring 2017
 * Project: Floyd-Warshall algorithm
 */
public class FloydWarshall {

    final static int INF = 999;

    static void shortestPath(int graph[][]) {
        int v = graph.length;
        int dist[][] = new int[v][v];

        // Matrix initialization
        for (int i = 0; i < v; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, v);
        }

        for (int k = 0; k < v; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < v; i++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < v; j++) {
                    // If vertex k is on the shortest path from i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distance matrix
        print(dist);
    }

    static void print(int dist[][]) {
        int v = dist.length;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    static int[][] generate() {
        final int DIAGONAL = 9999;
        int V = 225;
        int[][] graph = new int[V][V];

        // Initalize diagonals first
        for (int i = 0; i < V; i++) {
            graph[i][i] = DIAGONAL;
        }

        // A random distance from 1 to 10
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = new Random().nextInt(10);
            }
        }

        // Make sure there are INF distances also
        for (int i = 0; i < V; i++) {
            int a = new Random().nextInt(V);
            int b = new Random().nextInt(V);
            if (graph[a][b] != DIAGONAL) {
                graph[a][b] = INF;
            }
        }

        // Reset disagonals to 0
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == DIAGONAL) {
                    graph[i][j] = 0;
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        /*int graph[][] = {{0, 5, INF, 10},
        {INF, 0, 3, INF},
        {INF, INF, 0, 1},
        {INF, INF, INF, 0}
        };
        shortestPath(graph);*/

        shortestPath(generate());
        new Scanner(System.in).next();

    }

}
