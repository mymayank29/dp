package dp;

import java.util.HashMap;
import java.util.Map;

public class MinEdit {
	/*
	 * 
	 * 	dp[n][m] = m , n==0
	 * 			 = n , m==0
	 * 			 = dp[n-1][m-1] , A[n-1] == B[m-1]  
	 * 			 = 
	 * 
	 * */
	public static int minEdit(String A, String B, int n, int m) {
		if(n==0) {
			return m;
		}
		else if(m==0) {
			return n;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			return minEdit(A,B,n-1, m-1);
		}
		else {
			return 1 + Math.min(minEdit(A,B,n, m-1),
						Math.min(minEdit(A,B,n-1, m), minEdit(A,B,n-1, m-1)));
		}
	}
	
	public static int minEditMemo(String A, String B, int n, int m, Map<String, Integer> map) {
		
		if(map.containsKey(n+" "+m)) {
			return map.get(n+" "+m);
		}
		
		if(n==0) {
			map.put(n+" "+m, m);
			return m;
		}
		else if(m==0) {
			map.put(n+" "+m, n);
			return n;
		}
		else if(A.charAt(n-1) == B.charAt(m-1)) {
			int res =  minEditMemo(A,B,n-1, m-1,map);
			map.put(n+" "+m, res);
			return res;
		}
		else {
			int res =  1 + Math.min(minEditMemo(A,B,n, m-1, map),
						Math.min(minEditMemo(A,B,n-1, m, map), minEditMemo(A,B,n-1, m-1, map)));
			map.put(n+" "+m, res);
			return res;
		}
	}
	
	public static int minEditDp(String A, String B, int n, int m) {
		int[][] dp = new int[n+1][m+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if(i==0) {
					dp[i][j] = j;
				}
				else if(j==0) {
					dp[i][j] = i;
				}
				else if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		String A = "abcdef";
		String B = "azced";
		System.out.println(minEdit(A,B, A.length(), B.length()));
		Map<String, Integer> map = new HashMap<>();
		System.out.println(minEditMemo(A,B, A.length(), B.length(), map));
		System.out.println(minEditDp(A,B, A.length(), B.length()));

	}

}
