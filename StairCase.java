package dp;

import java.util.HashMap;
import java.util.Map;

public class StairCase {
	/*
	 * if we can only take not more than 2 steps at a time
	 * dp[n] =  n, n<=2
	 * 		 =	dp[n-1] + dp[n-2]
	 * 
	 * */
	// recursion
	public static long totalWaysRecursion(int n) {
		if(n<=2) {
			return n;
		}
		return totalWaysRecursion(n-1) + totalWaysRecursion(n-2);
	}
	
	// recursion with Memo
	public static long totalWaysRecursionMemo(int n, Map<Integer, Long> map) {
		if(map.containsKey(n)) {
			return map.get(n);
		}
		if(n<=2) {
			map.put(n, (long)n);
			return n;
		}
		long res = totalWaysRecursionMemo(n-1, map) + totalWaysRecursionMemo(n-2, map);
		map.put(n, res);
		return res;
	}
	
	// dp
	public static long totalWaysDp(int n) {
		long[] dp = new long[n+1];
		for(int i=0;i<=2;i++) {
			dp[i]  = (long)i;
		}
		for(int i=3;i<=n;i++) {
			dp[i] = dp [i-1] + dp[i-2];
		}
		return dp[n];
		
	}
	public static long totalWaysDp2(int n) {
		if(n<=2) {
			return (long)n;
		}
		long a = 1;
		long b = 2;
		for(int i=3;i<=n;i++) {
			long c = a + b;
			a = b;
			b = c;
		}
		return b;
		
	}
	
	public static void main(String[] args) {
		System.out.println(totalWaysRecursion(50));
		Map<Integer, Long> map = new HashMap<>();
		System.out.println(totalWaysRecursionMemo(50, map));
		System.out.println(totalWaysDp(50));
		System.out.println(totalWaysDp2(50));
	}

}
