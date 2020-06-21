package dp;

import java.util.HashMap;
import java.util.Map;

public class Lps {
	/*
	 * 
	 * 
	 * lps[i][j] = 1							, i=j
	 * 			 = 2 + lps[i+1][j-1]    		, A[i]==A[j]
	 * 		     = Math.max(lps[i][j-1], lps[i+1][j])
	 * */
	// recursion
	public static int lpsRecursion(String A, int i, int j) {
		if(i>j) {
			return 0;
		}
		if(i==j) {
			return 1;
		}
		if(A.charAt(i) == A.charAt(j)) {
			return 2 + lpsRecursion(A,i+1,j-1);
		}
		else {
			return Math.max(lpsRecursion(A,i,j-1), lpsRecursion(A,i+1,j));
		}
	}
	
	// recursion with Memo
	public static int lpsRecursionMemo(String A, int i, int j, Map<String, Integer> map) {
		String key = i+" "+j;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		
		if(i>j) {
			map.put(key, 0);
			return 0;
		}
		if(i==j) {
			map.put(key, 1);
			return 1;
		}
		if(A.charAt(i) == A.charAt(j)) {
			int res =  2 + lpsRecursionMemo(A,i+1,j-1,map);
			map.put(key, res);
			return res;
		}
		else {
			int res =  Math.max(lpsRecursionMemo(A,i,j-1,map), lpsRecursionMemo(A,i+1,j,map));
			map.put(key, res);
			return res;
		}
	}
	
	// dp
	public static int lpsDp(String A) {
		int n = A.length();
		int[][] dp = new int[n][n];
		for(int i=0;i<n;i++) {
			dp[i][i] = 1;
		}
		for(int l=2;l<=n;l++) {
			for(int i=0;i<n-l+1;i++) {
				int j=i+l-1;
				if(A.charAt(i) == A.charAt(j)) {
					dp[i][j] = 2 + dp[i+1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		String A = "agbdba";
		System.out.println(lpsRecursion(A, 0, A.length()-1));
		Map<String, Integer> map = new HashMap<>();
		System.out.println(lpsRecursionMemo(A, 0, A.length()-1, map));
		System.out.println(lpsDp(A));
	}

}
