package dp;

import java.util.HashMap;
import java.util.Map;

public class RodCutting {
	/*
	 * 
	 * dp[n] = 0						, n=0
	 * 		 = profit[n-1]				, n=1
	 * 		 = max(profit[k-1]+dp[n-k])
	 * 			k->1 to n
	 * 
	 * 
	 * */
	
	// recursion
	public static int maxProfitRecursion(int[] profit, int n) {
		if(n == 0) {
			return 0;
		}
		else if(n == 1) {
			return profit[n-1];
		}
		int res = 0;
		for(int k=1;k<=n;k++) {
			res = Math.max(res, profit[k-1] + maxProfitRecursion(profit,n-k));
		}
		return res;
	}
	// recursion with Memo
		public static int maxProfitRecursionMemo(int[] profit, int n, Map<Integer, Integer> map) {
			if(map.containsKey(n)) {
				return map.get(n);
			}
			
			if(n == 0) {
				map.put(0,0);
				return 0;
			}
			else if(n == 1) {
				map.put(1, profit[n-1]);
				return profit[n-1];
			}
			int res = 0;
			for(int k=1;k<=n;k++) {
				res = Math.max(res, profit[k-1] + maxProfitRecursionMemo(profit,n-k, map));
			}
			map.put(n, res);
			return res;
		}
		
		// dp
		public static int maxProfitDp(int[] profit, int n) {
			int[] dp = new int[n+1];
			dp[0] = 0;
			for(int i=1;i<=n;i++) {
				dp[i] = profit[i-1];
				for(int j=1;j<=i;j++) {
					dp[i] = Math.max(dp[i], profit[j-1] + dp[i-j]);
				}
			}
			return dp[n];
		}
		
	public static void main(String[] args) {
		int profit[] = {1, 5, 8, 9, 10, 17, 17, 20};
		System.out.println(maxProfitRecursion(profit, profit.length));
		Map<Integer, Integer> map = new HashMap<>();
		System.out.println(maxProfitRecursionMemo(profit, profit.length, map)); 
		System.out.println(maxProfitDp(profit, profit.length));
	}

}
