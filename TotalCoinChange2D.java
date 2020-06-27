package dp;

import java.util.HashMap;
import java.util.Map;

public class TotalCoinChange2D {
	/*
	 * 
	 *
	 * dp[n][v] 	=  1				, v = 0
	 * 				=  0				, n=0
	 * 				=  dp[n-1][v]  , v < coins[n-1]
	 * 				=  dp[n-1][v] + dp[n][v-coins[n-1]]
	 * 
	 * 
	 * */
	
	// recursion
	public static int totalCoinRecursion(int[] coins, int n, int v) {
		if(v == 0) {
			return 1;
		}
		else if(n==0) {
			return 0;
		}
		else if(v < coins[n-1]) {
			return totalCoinRecursion(coins, n-1, v);
		}
		else {
			return  totalCoinRecursion(coins, n-1, v) + totalCoinRecursion(coins, n, v-coins[n-1]);
		}
	}
	
	// recursion with memo
	public static int totalCoinRecursionMemo(int[] coins, int n, int v, Map<String, Integer> map) {
		String key = n+" "+v;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		if(v == 0) {
			map.put(key,1);
			return 1;
		}
		else if(n==0) {
			map.put(key,0);
			return 0;
		}
		else if(v < coins[n-1]) {
			int res =  totalCoinRecursionMemo(coins, n-1, v, map);
			map.put(key, res);
			return res;
		}
		else {
			int res = totalCoinRecursionMemo(coins, n-1, v, map) + totalCoinRecursionMemo(coins, n, v-coins[n-1], map);
			map.put(key, res);
			return res;
		}
	}
	
	// dp
	public static int totalCoinDp(int[] coins, int n, int v) {
		int[][] dp = new int[n+1][v+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=v;j++) {
				if(j == 0) {
					dp[i][j] = 1;
				} else if(i==0) {
					dp[i][j] = 0;
				} else if(j < coins[i-1]) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
				}
			}
		}
		return dp[n][v];
	}
	
	public static void main(String[] args) {
		int[] coins = {1,2,3};
		int val = 5;
		System.out.println(totalCoinRecursion(coins,coins.length,val));
		Map<String, Integer> map = new HashMap<>();
		System.out.println(totalCoinRecursionMemo(coins, coins.length,val, map));
		System.out.println(totalCoinDp(coins,coins.length,val));
	}

}
