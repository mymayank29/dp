package dp;

import java.util.*;

public class Lcs {
	
	/*
	 * 
	 *   dp[n][m] = 0 									, n==0 || m == 0
	 *   		  = 1 + dp[n-1][m-1] 					, A[n-1]==B[m-1]
	 *   		  = Math.max(dp[n-1][m], dp[n][m-1])	, else
	 * 
	 * 
	 * */
	// recursion
	public static int lcsRecursion(String A, String B, int n, int m) {
		if(n==0 || m==0) {
			return 0;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			return 1 + lcsRecursion(A,B,n-1,m-1);
		}
		return Math.max(lcsRecursion(A,B,n-1,m), lcsRecursion(A,B,n,m-1));
	}
	
	// recursion memo
	public static int lcsRecursionMemo(String A, String B, int n, int m, 
			Map<String, Integer> map) {
		if(map.containsKey(n+" "+m)) {
			return map.get(n+" "+m);
		}
		else if(n==0 || m==0) {
			map.put(n+" "+m, 0);
			return 0;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			int res =  1 + lcsRecursionMemo(A,B,n-1,m-1,map);
			map.put(n+" "+m, res);
			return res;
		}
		int res =  Math.max(lcsRecursionMemo(A,B,n-1,m,map), lcsRecursionMemo(A,B,n,m-1,map));
		map.put(n+" "+m, res);
		return res;
	}
	public static int lcsRecursionMemo(String A, String B, int n, int m) {
		Map<String, Integer> map = new HashMap<>();
		return lcsRecursionMemo(A,B,n,m,map);
	}
	
	// dp
	public static int lcsDp(String A, String B, int n, int m) {
		int[][] dp = new int[n+1][m+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				}
				else if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		String A = "abcdef";
		String B = "acbcf";
		System.out.println(lcsRecursion(A,B, A.length(), B.length()));
		System.out.println(lcsRecursionMemo(A,B, A.length(), B.length()));
		System.out.println(lcsDp(A,B, A.length(), B.length()));
	}

}
