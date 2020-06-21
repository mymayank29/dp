package dp;
import java.util.*;

public class Knapsack {
	/*
	 * 
	 * 
	 * dp[i][j] --> max weight i in j items
	 * 
	 * dp[i][j] = 0 										, i==0 || j==0;
	 * 			= dp[i][j-1]								, i > weight[j-1]
	 * 			= Math.max(dp[i][j-1], value[j-1] + dp[i-weight[i-1]][j-1])
	 * */
	// recursion
	public static int knapsack(int[] weight, int[] value, int W, int n) {
		
		// if no items or net Weight is 0
		if(n==0 || W == 0) {
			return 0;
		}
		if(W < weight[n-1]) {
			return knapsack(weight, value, W, n-1);
		}
		return Math.max(knapsack(weight, value, W, n-1), value[n-1] + knapsack(weight, value, W-weight[n-1], n-1));
		
	}
	
	// recursion with memo
		public static int knapsackMemo(int[] weight, int[] value, int W, int n) {
		
			Map<String, Integer> map = new HashMap<>();
			return knapsackMemo(weight, value, W, n, map);
		
		}
		public static int knapsackMemo(int[] weight, int[] value, int W, int n, Map<String, Integer> map) {
			
			if(map.containsKey(W+" "+n)) {
				return map.get(W+" "+n);
			}
			// if no items or net Weight is 0
			if(n==0 || W == 0) {
				map.put(W+" "+n, 0);
				return 0;
			}
			if(W < weight[n-1]) {
				int res = knapsackMemo(weight, value, W, n-1);
				map.put(W+" "+n, res);
				return res;
			}
			int res = Math.max(knapsackMemo(weight, value, W, n-1), value[n-1] + knapsackMemo(weight, value, W-weight[n-1], n-1));
			map.put(W+" "+n, res);
			return res;
			
		}
		
		// alternate recursion with memo
		public static int knapsackMemo2(int[] weight, int[] value, int W, int n) {
			
			Map<Integer, Integer> map = new HashMap<>();
			return knapsackMemo2(weight, value, W, n, map);
		
		}
		public static int knapsackMemo2(int[] weight, int[] value, int W, int n, Map<Integer, Integer> map) {
			
			if(map.containsKey(W)) {
				return map.get(W);
			}
			// if no items or net Weight is 0
			if(n==0 || W == 0) {
				map.put(W, 0);
				return 0;
			}
			if(W < weight[n-1]) {
				int res = knapsackMemo2(weight, value, W, n-1);
				map.put(W, res);
				return res;
			}
			int res = Math.max(knapsackMemo2(weight, value, W, n-1), value[n-1] + knapsackMemo2(weight, value, W-weight[n-1], n-1));
			map.put(W, res);
			return res;
			
		}
	
	// dp
	public static int knapsackDp(int[] weight, int[] value, int W, int n) {
		int[][] dp = new int[W+1][n+1];
		for(int i=0;i<=W;i++) {
			for(int j=0;j<=n;j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				}
				else if(i < weight[j-1]) {
					dp[i][j] = dp[i][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], value[j-1] + dp[i-weight[j-1]][j-1]);
				}
			}
		}
		return dp[W][n];
	}
	public static void main(String[] args) {
		int[] weight = {1,3,4,5};
		int[] value = {1,4,5,7};
		System.out.println(knapsack(weight,value, 7, weight.length));
		System.out.println(knapsackMemo(weight,value, 7, weight.length));
		System.out.println(knapsackMemo2(weight,value, 7, weight.length));
		System.out.println(knapsackDp(weight,value, 7, weight.length));

	}

}
