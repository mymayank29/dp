package dp;

import java.util.Arrays;

public class TotalWaysMatrix {
	/*
	 * 
	 *  m*n -> matrix
	 *	dp[m][n] = 1						, m=1 || n=1
	 *			 = dp[m-1][n] + dp[m][n-1]
	 * 
	 * 
	 * */
	// recursion
	public static int totalWaysRecursion(int m, int n) {
		if(m==1 || n==1) {
			return 1;
		}
		else {
			return totalWaysRecursion(m-1,n) + totalWaysRecursion(m,n-1);
		}
	}
	
	// dp
	public static int totalWaysDp(int m, int n) {
		int[][] dp = new int[m][n];
		for(int i=0;i<m;i++) {
			dp[i][0] = 1;
		}
		for(int i=0;i<n;i++) {
			dp[0][i] = 1;
		}
		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}
	
	// space optimised Dp
	public static int totalWaysDpOptimised(int m, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				dp[j] += dp[j-1];
			}
		}
		return dp[n-1];
	}
	
	public static void main(String[] args) {
		System.out.println(totalWaysRecursion(4,4));
		System.out.println(totalWaysDp(4,4));
		System.out.println(totalWaysDpOptimised(4,4));
	}

}
