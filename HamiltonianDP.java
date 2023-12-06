import java.util.Arrays;

class Hamiltonian_DP {

	static boolean HamiltonianDP(int adj[][], int N) {
		boolean dp[][] = new boolean[N][(1 << N)];

		for(int i = 0; i < N; i++)
			dp[i][(1 << i)] = true;

		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					for(int k = 0; k < N; k++) {
						if ((i & (1 << k)) != 0 && 
							adj[k][j] == 1 && j != k && 
							dp[k][i ^ (1 << j)]) {
							dp[j][i] = true;
							break;
						}
					}
				}
			}
		}

		for(int i = 0; i < N; i++) {
			if (dp[i][(1 << N) - 1])
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] graph = GraphGenerator.generateRandomGraph(4);
		GraphGenerator.printAdjMatrix(graph);
		int S = graph.length;

		GraphGenerator.saveGraphToFile(graph, "GraphHamiltonianDP20");
        int[][] graphh = GraphGenerator.readGraphFromFile("GraphHamiltonianDP20");
		System.out.print(Arrays.deepToString(graphh));

		Runtime runtime = Runtime.getRuntime();
		System.gc();
		long beforeUsedMemory = runtime.totalMemory() - runtime.freeMemory();
		long startTime = System.nanoTime();

		if (HamiltonianDP(graph, S))
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

