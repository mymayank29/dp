package dp;
import java.util.*;

public class MinCoinChange2D {
	/*
	 * dp[V][n] 		= 0									, V = 0
	 * 			= Integer.MAX_VALUE							, n=0
	 * 			= dp[V][n-1]								, V < coins[n-1]
	 * 			= Math.min(dp[V][n-1], dp[V-coins[n-1]][n])				, else
	 * */
	
	// recursion
	public static int minCoinRecursion(int[] coins, int V) {
		int res = minCoinRecursion(coins,coins.length,V);
		return res == Integer.MAX_VALUE ? -1 : res;
	}
	public static int minCoinRecursion(int[] coins, int n, int V) {
		if(V == 0) {
			return 0;
		}
		else if(n == 0) {
			return Integer.MAX_VALUE;
		}
		
		else {
			if(coins[n-1] <= V && minCoinRecursion(coins,n,V-coins[n-1]) != Integer.MAX_VALUE) {
				return Math.min(minCoinRecursion(coins,n-1,V), 1 +  minCoinRecursion(coins,n,V-coins[n-1]));
			}
			return minCoinRecursion(coins,n-1,V);
		}

	}
	
	
	// recursion with memo
	public static int minCoinRecursionMemo(int[] coins, int V) {
		Map<String, Integer> map = new HashMap<>();
		int res = minCoinRecursionMemo(coins,coins.length,V,map);
		return res == Integer.MAX_VALUE ? -1 : res;
	}
	public static int minCoinRecursionMemo(int[] coins, int n, int V, Map<String, Integer> map) {
		if(map.containsKey(n+" "+V)) {
			return map.get(n+" "+V);
		}
		if(V == 0) {
			map.put(n+" "+V, 0);
			return 0;
		}
		else if(n == 0) {
			map.put(n+" "+V, Integer.MAX_VALUE);
			return Integer.MAX_VALUE;
		}
		else {
			if(coins[n-1] <= V && minCoinRecursionMemo(coins,n,V-coins[n-1],map) != Integer.MAX_VALUE) {
				int res =  Math.min(minCoinRecursionMemo(coins,n-1,V,map), 1 +  minCoinRecursionMemo(coins,n,V-coins[n-1],map));
				map.put(n+" "+V, res);
				return res;
			}
			int res =  minCoinRecursionMemo(coins,n-1,V,map);
			map.put(n+" "+V, res);
			return res;
		}
	}
	
	// recursion with alternate memo
		public static int minCoinRecursionMemo2(int[] coins, int V) {
			Map<Integer, Integer> map = new HashMap<>();
			int res = minCoinRecursionMemo2(coins,coins.length,V,map);
			return res == Integer.MAX_VALUE ? -1 : res;
		}
		public static int minCoinRecursionMemo2(int[] coins, int n, int V, Map<Integer, Integer> map) {
			if(map.containsKey(V)) {
				return map.get(V);
			}
			if(V == 0) {
				map.put(V, 0);
				return 0;
			}
			else if(n == 0) {
				map.put(V, Integer.MAX_VALUE);
				return Integer.MAX_VALUE;
			}
			else {
				if(coins[n-1] <= V && minCoinRecursionMemo2(coins,n,V-coins[n-1],map) != Integer.MAX_VALUE) {
					int res =  Math.min(minCoinRecursionMemo2(coins,n-1,V,map), 1 +  minCoinRecursionMemo2(coins,n,V-coins[n-1],map));
					map.put(V, res);
					return res;
				}
				int res =  minCoinRecursionMemo2(coins,n-1,V,map);
				map.put(V, res);
				return res;
			}
		}
	
	// dp
	public static int minCoinRecursionDp(int[] coins, int V) {
		int[][] dp = new int[coins.length+1][V+1];
		for(int i=0;i<=coins.length;i++) {
			for(int j=0;j<=V;j++) {
				if(j==0) {
					dp[i][j] = 0;
				}
				else if(i==0) {
					dp[i][j] = Integer.MAX_VALUE;
				}
				
				else {
					dp[i][j] = dp[i-1][j];
					if (j >= coins[i-1] && dp[i][j-coins[i-1]] != Integer.MAX_VALUE ) {
						dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j-coins[i-1]]);
					}
				}
			}
		}
		return dp[coins.length][V] == Integer.MAX_VALUE ? -1 : dp[coins.length][V];
	}
	

	public static void main(String[] args) {
		int[] coins = {1,5,6,8};
		System.out.println(minCoinRecursion(coins,11));
		System.out.println(minCoinRecursionMemo(coins,11));
		System.out.println(minCoinRecursionMemo2(coins,11));
		System.out.println(minCoinRecursionDp(coins,11));
	}

}
