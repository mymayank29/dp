package dp;

import java.util.HashMap;
import java.util.Map;

public class MinCoinChange1D {
	/*
	 * 
	 * 
	 * dp[V] 		= 		0, V=0
	 * 		 		=		min(1+dp[V - coins[i])
	 * 					i -> 0 to n-1 && coins[i] <= V	 
	 * 
	 * 
	 * */
	// recursion
	private static int minCoinRecursion(int[] coins, int V) {
		if(V == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for(int i=0;i<coins.length;i++) {
			if(V >= coins[i]) {
				int val = minCoinRecursion(coins, V-coins[i]);
				min = Math.min(min, val);
			}
		}
		return min == Integer.MAX_VALUE ? min : min + 1;
	}
	
	
	// recursion with memo
	private static int minCoinRecursionMemo(int[] coins, int V, Map<Integer, Integer> map) {
		if(map.containsKey(V)) {
			return map.get(V);
		}
		if(V == 0) {
			map.put(V, 0);
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for(int i=0;i<coins.length;i++) {
			if(V >= coins[i]) {
				int val = minCoinRecursionMemo(coins, V-coins[i], map);
				min = Math.min(min, val);
			}
		}
		int res = min == Integer.MAX_VALUE ? min : min + 1;
		map.put(V, res);
		return res;
	}
	
	// dp
	private static int minCoinDp(int[] coins, int V) {
		int[] dp = new int[V+1];
		dp[0] = 0;
		for(int i=1;i<=V;i++) {
			dp[i] = Integer.MAX_VALUE;
			for(int j=0;j<coins.length;j++) {
				if(coins[j] <= i && dp[i-coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1 + dp[i-coins[j]]);
				}
			}
		}
		return dp[V];
	}
	public static void main(String[] args) {
		int[] coins = {1,5,6,8};
		System.out.println(minCoinRecursion(coins,11));
		Map<Integer, Integer> map = new HashMap<>();
		System.out.println(minCoinRecursionMemo(coins,11,map));
		System.out.println(minCoinDp(coins,11));
	}

	

}
