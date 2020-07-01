package dp;

public class MaxSubSquare {
	
	public static int subSquareMatrix(int[][] M) {
		int maxVal = 0;
		for(int i=1;i<M.length;i++) {
			for(int j=1;j<M[0].length;j++) {
				if(M[i][j] == 1) {
					M[i][j] = 1 + Math.min(M[i-1][j-1], Math.min(M[i-1][j], M[i][j-1]));
					maxVal = Math.max(maxVal, M[i][j]);
				}
			}
		}
		return maxVal;
	}

	public static void main(String[] args) {
		 int M[][] = {{0, 1, 1, 0, 1},  
                 	{1, 1, 0, 1, 0},  
                 	{0, 1, 1, 1, 0}, 
                 	{1, 1, 1, 1, 0}, 
                 	{1, 1, 1, 1, 1}, 
                 	{0, 0, 0, 0, 0}}; 
		 System.out.println(subSquareMatrix(M));

	}

}
