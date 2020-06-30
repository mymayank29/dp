package dp;

import java.util.HashMap;
import java.util.Map;

public class MinCost2DMatrix {
	
	/*
	 * 
	 * dp[r][c] =  	cost[n][m], n==0&&m==0
	 * 			=	cost[n][m] + min(dp[n-1][m], dp[n][m-1], dp[n-1][m-1])
	 * 											
	 * 
	 * */
	
	// recurison
	public static int minCostRecursion(int[][] cost, int r, int c) {
		if(r<0 || c<0) {
			return Integer.MAX_VALUE;
		}
		if(r == 0 && c == 0) {
			return cost[r][c];
		}
		else {
			return cost[r][c] + Math.min(minCostRecursion(cost,r-1,c-1), Math.min(minCostRecursion(cost,r-1,c), minCostRecursion(cost,r,c-1)));
		}
	}
	
	// recursion with Memo
	public static int minCostRecursionMemo(int[][] cost, int r, int c, Map<String, Integer> map) {
		String key = r+" "+c;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		if(r<0 || c<0) {
			map.put(key, Integer.MAX_VALUE);
			return Integer.MAX_VALUE;
		}
		if(r == 0 && c == 0) {
			map.put(key, cost[r][c]);
			return cost[r][c];
		}
		else {
			int res =  cost[r][c] + Math.min(minCostRecursion(cost,r-1,c-1), Math.min(minCostRecursion(cost,r-1,c), minCostRecursion(cost,r,c-1)));
			map.put(key, res);
			return res;
		}
	}
	
	// dp
	public static int minCostDp(int[][] cost, int r, int c) {
		int[][] dp = new int[r+1][c+1];
		dp[0][0] = cost[0][0];
		for(int i=1;i<=r;i++) {
			dp[i][0] = cost[i][0] + dp[i-1][0];
		}
		for(int i=1;i<=c;i++) {
			dp[0][i] = cost[0][i] + dp[0][i-1];
		}
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				dp[i][j] = cost[i][j] + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
			}
		}
		return dp[r][c];
	}

	public static void main(String[] args) {
		 int cost[][] = { {1, 2, 3}, 
                 		{4, 8, 2}, 
                 		{1, 5, 3} };       
		 System.out.println(minCostRecursion(cost, 2, 2)); 
		 Map<String, Integer> map = new HashMap<>();
		 System.out.println(minCostRecursionMemo(cost, 2, 2, map)); 
		 System.out.println(minCostDp(cost, 2, 2)); 
	}

}
