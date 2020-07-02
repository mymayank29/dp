package dp;

public class ImmutableSumMatrix {
	static int[][] temp;
	public static void prefixSum(int[][] mat) {
		int r = mat.length;
		int c = mat[0].length;
		temp = new int[r+1][c+1];
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				temp[i][j] = mat[i-1][j-1] + temp[i-1][j] + 
							temp[i][j-1] - temp[i-1][j-1];
			}
		}
	}
	public static int getSum(int r1, int c1, int r2, int c2) {
		r1++;
		c1++;
		r2++;
		c2++;
		return temp[r2][c2] - temp[r2][c1-1] - temp[r1-1][c2] + temp[r1-1][c1-1];
	}
	public static void main(String[] args) {
		int[][] mat = { 
				{2,0,-3,4},
				{6,3,2,-1},
				{5,4,7,3},
				{2,-6,8,1}
		};
		prefixSum(mat);
		System.out.println(getSum(1,0,2,1));
		System.out.println(getSum(0,2,2,3));
	}

}
