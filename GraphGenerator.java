import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GraphGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah vertex: ");
        int vertexCount = scanner.nextInt();

        int[][] adjMatrix = generateRandomGraph(vertexCount);

        System.out.println("Matriks Tetangga:");
        printAdjMatrix(adjMatrix);
    }

    public static int[][] generateRandomGraph(int vertexCount) {
        Random random = new Random();
        int[][] adjMatrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                int edgeValue = random.nextInt(2);
                adjMatrix[i][j] = edgeValue;
                adjMatrix[j][i] = edgeValue;
            }
        }

        return adjMatrix;
    }

    public static void printAdjMatrix(int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void saveGraphToFile(int[][] graph, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int rows = graph.length;
            int cols = graph[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.print(graph[i][j] + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] readGraphFromFile(String fileName) {
        int[][] graph = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            if ((line = reader.readLine()) != null) {
                String[] size = line.split(" ");

                graph = new int[size.length][size.length];
                for (int h = 0; h < size.length; h++) {
                    graph[0][h] = Integer.parseInt(size[h]);
                }

                for (int i = 1; i < size.length; i++) {
                    if ((line = reader.readLine()) != null) {
                        String[] elements = line.split(" ");
                        for (int j = 0; j < size.length; j++) {
                            graph[i][j] = Integer.parseInt(elements[j]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

}
