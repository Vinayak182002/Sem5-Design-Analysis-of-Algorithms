// MTE Mock Exam
// Assignemnt No.: 03
// Floyd Warshall Algorithm in Java

class FloydWarshall 
{
	final static int INF = 9999, nV = 4;

	// Implementing floyd warshall algorithm
	void floydWarshall(int graph[][]) 
	{
		int matrix[][] = new int[nV][nV];
    		int i, j, k;

    		for (i = 0; i < nV; i++)
      			for (j = 0; j < nV; j++)
        			matrix[i][j] = graph[i][j];

		// Adding vertices individually
    		for (k = 0; k < nV; k++) 
		{
      			for (i = 0; i < nV; i++) 
			{
        			for (j = 0; j < nV; j++) 
				{
          				if (matrix[i][k] + matrix[k][j] < matrix[i][j])
            					matrix[i][j] = matrix[i][k] + matrix[k][j];
       				 }
      			}
    		}
		System.out.println("\nThe shortest path matrix calculated using Floyd Warshall Algorithm is==>");
    		printMatrix(matrix);
  	}

	void printMatrix(int matrix[][]) 
	{
		System.out.println("\n===========================================================================");
    		for (int i = 0; i < nV; ++i) 
		{
      			for (int j = 0; j < nV; ++j) 
			{
        			if (matrix[i][j] == INF)
          				System.out.print("INFINITY ");
        			else
          				System.out.print(matrix[i][j] + "  ");
      			}
			System.out.println();
    		}
		System.out.println("\n===========================================================================");
  	}

 	public static void main(String[] args) 
	{
		int graph[][] = { { 0, 3, 6, 5 }, { 2, 0, INF, 4 }, { 8, 1, 0, INF }, { INF, INF, 2, 0 } };
		FloydWarshall a = new FloydWarshall();
		System.out.println("\nThe original path matrix is==>");
		a.printMatrix(graph);
    		a.floydWarshall(graph);
  	}
}