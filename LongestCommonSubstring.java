package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubstring {
	/*
	 * 
	 * calculate longest common suffix first for all combinations
	 * out of that pick maximum
	 * 
	 * dp[n][m] = 0 				, n==0||m==0
	 * 			= 1 + dp[n-1][m-1]  , A[n-1] == B[m-1]
	 * 			= 0			
	 * return max(dp[i][j]) i from 0 to n, j from 0 to n
	 * 
	 * */
	private static int lcsRecursionHelper(String A, String B, int n, int m) {
		int res = 0;
		if(n==0 || m==0) {
			res = 0;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			res = 1 + lcsRecursionHelper(A, B, n-1, m-1);
		}
		return res;
	}
	// recursion
	public static int lcsRecursion(String A, String B, int n, int m) {
		int maxSub = 0;
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				maxSub = Math.max(maxSub, lcsRecursionHelper(A,B,i,j));
			}
		}
		return maxSub;
	}
	
	public static int lcsRecursionMemo(String A, String B, int n, int m, Map<String, Integer> map) {
		String key = n+" "+m;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		int res = 0;
		if(n==0 || m==0) {
			res = 0;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			res = 1 + lcsRecursionHelper(A, B, n-1, m-1);
		}
		map.put(key, res);
		return res;
		
	}
	
	// recursion with Memo
	public static int lcsRecursionMemo(String A, String B, int n, int m) {
		int maxSub = 0;
		Map<String, Integer> map = new HashMap<>();
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				maxSub = Math.max(maxSub, lcsRecursionMemo(A,B,i,j, map));
			}
		}
		return maxSub;
	}
	
	// dp
	public static int lcsRecursionDp(String A, String B, int n, int m) {
		int[][] dp = new int[n+1][m+1];
		int maxSub = 0;
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if(i==0||j==0) {
					dp[i][j] = 0;
				}
				else if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				maxSub = Math.max(maxSub, dp[i][j]);
			}
		}
		return maxSub;
	}
	
	
	public static void main(String[] args) {
		String A = "abcdaf";
		String B = "zbcdf";
		System.out.println(lcsRecursion(A,B, A.length(), B.length()));
		System.out.println(lcsRecursionMemo(A,B, A.length(), B.length()));
		System.out.println(lcsRecursionDp(A,B, A.length(), B.length()));
	}

}
