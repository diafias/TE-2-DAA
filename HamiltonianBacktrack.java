import java.util.Arrays;

class HamiltonianBacktrack {
    int path[];
    boolean isSafe(int v, int graph[][], int path[], int pos) {
        if (graph[path[pos - 1]][v] == 0)
            return false;

        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;
        return true;
    }

    boolean hamCycleUtil(int graph[][], int path[], int pos, int verteks) {
        if (pos == verteks) {
            return true;
        }

        for (int v = 1; v < verteks; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamCycleUtil(graph, path, pos + 1, verteks) == true)
                    return true;
                path[pos] = -1;
            }
        }
        return false;
    }

    int hamCycle(int graph[][]) {
        int verteks = graph.length;
        path = new int[verteks];
        for (int i = 0; i < verteks; i++)
            path[i] = -1;

        path[0] = 0;
        if (hamCycleUtil(graph, path, 1, verteks) == false) {
            return 0;
        }

        return 1;
    }
   
    public static void main(String args[]) {
        HamiltonianBacktrack hamiltonian = new HamiltonianBacktrack();

        int[][] graph = GraphGenerator.generateRandomGraph(200);
        GraphGenerator.printAdjMatrix(graph);
        GraphGenerator.saveGraphToFile(graph, "GraphHamiltonianBT20");
        int[][] graphh = GraphGenerator.readGraphFromFile("GraphHamiltonianBT20");
        System.out.print(Arrays.deepToString(graphh));

        Runtime runtime = Runtime.getRuntime();
        System.gc();
        long beforeUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        if (hamiltonian.hamCycle(graph) == 1)
            System.out.println("YES");
        else
            System.out.println("NO");


        long endTime = System.nanoTime();
        long afterUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        long usedMemoryBytes = afterUsedMemory - beforeUsedMemory;
        double usedMemoryKB = (double) usedMemoryBytes / 1024;

        System.out.println("Penggunaan Memori = " + usedMemoryKB + " KB");
        System.out.println("Penggunaan Runtime = " + (endTime - startTime) + " ns");
        System.out.println("");
    }
}