package dp;

import java.util.HashMap;
import java.util.Map;

public class WildCard {
	/*
	 * 
	 * 
	 * dp[m][n] =	false					, m=0 no pattern but text
	 * 			=	false					, n=0 && pattern[m-1]!='*'	
	 * 			=	dp[m-1][n]				, n=0 && pattern[m-1] = '*'
	 * 			=	dp[m-1][n-1]			, pattern[m-1]==text[n-1] || pattern[m-1]=='?'
	 * 			=	dp[m-1][n]||dp[m][n-1]	, pattern[m-1] == '*'
	 * */
	
	// recursion
	public static boolean wildCardRecursion(String pattern, String text, int m, int n) {
		if(m==0 && n==0) {
			return true;
		}
		if(m==0) {
			return false;
		}
		else if(n==0) {
			if(pattern.charAt(m-1) == '*') {
				return wildCardRecursion(pattern, text, m-1, n);
			}
			else {
				return false;
			}
		}
		else if(pattern.charAt(m-1) == text.charAt(n-1) || pattern.charAt(m-1) == '?') {
			return wildCardRecursion(pattern, text, m-1, n-1);
		}
		else if(pattern.charAt(m-1) == '*') {
			return wildCardRecursion(pattern,text,m-1,n) || wildCardRecursion(pattern, text, m, n-1);
		}
		else {
			return false;
		}
	}
	
	// recursion with Memo
		public static boolean wildCardRecursionMemo(String pattern, String text, int m, int n, Map<String, Boolean> map) {
			String key = m+" "+n;
			if(map.containsKey(key)) {
				return map.get(key);
			}
			if(m==0 && n==0) {
				map.put(key, true);
				return true;
			}
			if(m==0) {
				map.put(key, false);
				return false;
			}
			else if(n==0) {
				if(pattern.charAt(m-1) == '*') {
					boolean res =  wildCardRecursionMemo(pattern, text, m-1, n, map);
					map.put(key, res);
					return res;
				}
				else {
					map.put(key, false);
					return false;
				}
			}
			else if(pattern.charAt(m-1) == text.charAt(n-1) || pattern.charAt(m-1) == '?') {
				boolean res =  wildCardRecursionMemo(pattern, text, m-1, n-1, map);
				map.put(key, res);
				return res;
			}
			else if(pattern.charAt(m-1) == '*') {
				boolean res = wildCardRecursionMemo(pattern,text,m-1,n, map) || wildCardRecursionMemo(pattern, text, m, n-1, map);
				map.put(key, res);
				return res;
			}
			else {
				map.put(key, false);
				return false;
			}
		}
	
	// Dp
	public static boolean wildCardDp(String pattern, String text, int m, int n) {
		boolean[][] dp = new boolean[m+1][n+1];
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=n;j++) {
				if(i==0 && j==0) {
					dp[i][j] = true;
				}
				else if(i==0) {
					dp[i][j] = false;
				}
				else if(j==0) {
					if(pattern.charAt(i-1) == '*') {
						dp[i][j] = dp[i-1][j];
					}
					else {
						dp[i][j] = false;
					}
				}
				else if(pattern.charAt(i-1) == text.charAt(j-1) || pattern.charAt(i-1) == '?') {
					dp[i][j] = dp[i-1][j-1];
				}
				else if(pattern.charAt(i-1) == '*') {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				}
				else {
					dp[i][j] = false;
				}
			}
		}
		return dp[m][n];
	}
	public static void main(String[] args) {
		String pattern = "x?y*z";
		String text = "xaylmnz";
		System.out.println(wildCardRecursion(pattern, text, pattern.length(), text.length()));
		Map<String, Boolean> map = new HashMap<>();
		System.out.println(wildCardRecursionMemo(pattern, text, pattern.length(), text.length(), map));
		System.out.println(wildCardDp(pattern, text, pattern.length(), text.length()));
	}

}
