package dp;

import java.util.HashMap;
import java.util.Map;

public class TotalCoinChange1D {
	/*
	 * 
	 * dp[v] = 1, v = 0
	 * 		 = +(dp[v-coins[k-1]), v >= coins[k-1] 
	 * 		  k = 1 to n
	 * */
	
//	public static int totalCoinRecursion(int[] coins, int n, int v) {
//		if(v == 0) {
//			return 1;
//		}
//		else {
//			int res = 0;
//			for(int k=1;k<=n;k++) {
//				if(v >= coins[k-1]) {
//					res += totalCoinRecursion(coins, n, v-coins[k-1]);
//				}
//			}
//			return res;
//		}
//	}
//	// total(3) 				  			+		 total(2) 			+ total(1)
//	// total(2)+total(1)+total(0) 			+	  total(1) + total(0)   + total(0)
//	//total(1)+total(0)+total(1)+total(0)	+     total(0)+total(0)		+ 1
//	// 1 + 1 + 1 + 1						+    1+ 1				+ 1
//	
//	public static int totalCoinRecursionMemo(int[] coins, int n, int v, Map<Integer, Integer> map) {
//		if(map.containsKey(v)) {
//			return map.get(v);
//		}
//		if(v == 0) {
//			map.put(v,1);
//			return 1;
//		}
//		else {
//			int res = 0;
//			for(int k=1;k<=n;k++) {
//				if(v >= coins[k-1]) {
//					res += totalCoinRecursionMemo(coins, n, v-coins[k-1], map);
//				}
//			}
//			map.put(v, res);
//			return res;
//		}
//	}
//	
	public static int totalCoinDp(int[] coins, int n, int v) {
		int[] dp = new int[v+1];
		dp[0] = 1;

		for (int i=0; i<coins.length; i++) 
	           for (int j=coins[i]; j<=v; j++) 
	               dp[j] += dp[j-coins[i]]; 
		return dp[v];
	}
	public static void main(String[] args) {
		int[] coins = {1,2,3};
		int val = 4;
//		System.out.println(totalCoinRecursion(coins,coins.length,val));
//		Map<Integer, Integer> map = new HashMap<>();
//		System.out.println(totalCoinRecursionMemo(coins,coins.length,val, map));
		System.out.println(totalCoinDp(coins,coins.length,val));
	}

}
