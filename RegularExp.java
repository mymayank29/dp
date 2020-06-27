package dp;

import java.util.HashMap;
import java.util.Map;

public class RegularExp {
	/*
	 * 
	 * dp[p][t] = 	true     					, p==0 && t==0
	 * 			=   false	 					, p==0
	 * 			=   dp[p-2][t]					, t=0 , pattern[p-1] == '*'
	 * 			=   false						, t=0
	 * 			=   dp[p-1][t-1]    			, pattern[p-1]==text[t-1] || pattern[p-1] == '.'
	 * 			=   dp[p-2][t-1]				, pattern[p-1] = '*' && pattern[p-2] != text[t-1]
	 * 			=   dp[p-2][t-1] || dp[p][t-1]	, pattern[p-1] == '*'
	 * 			=   false						, else
	 * */
	
	// recursion
	public static boolean regRecursion(String pattern, String text, int p, int t) {
		if(p==0 && t ==0) {
			return true;
		}
		else if(p==0) {
			return false;
		}
		else if(t==0) {
			if(pattern.charAt(p-1) == '*') {
				return regRecursion(pattern, text, p-2, t);
			}
			return false;
		}
		else if(pattern.charAt(p-1) == text.charAt(t-1) || pattern.charAt(p-1) == '.') {
			return regRecursion(pattern, text, p-1, t-1);
		}
		else if(pattern.charAt(p-1) == '*' && (pattern.charAt(p-2) == text.charAt(t-1) || pattern.charAt(p-2) == '.' )) {
			return regRecursion(pattern, text, p-2, t) || regRecursion(pattern, text, p, t-1);
		}
		else if(pattern.charAt(p-1) == '*') {
			return regRecursion(pattern, text, p-2, t);
		}
		else {
			return false;
		}
	}
	
	// recursion with memo
	public static boolean regRecursionMemo(String pattern, String text, int p, int t, Map<String, Boolean> map) {
		String key = p+" "+t;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		
		if(p==0 && t ==0) {
			map.put(key, true);
			return true;
		}
		else if(p==0) {
			map.put(key, false);
			return false;
		}
		else if(t==0) {
			if(pattern.charAt(p-1) == '*') {
				boolean res =  regRecursionMemo(pattern, text, p-2, t, map);
				map.put(key, res);
				return res;
			}
			else {
				map.put(key, false);
				return false;
			}
		}
		else if(pattern.charAt(p-1) == text.charAt(t-1) || pattern.charAt(p-1) == '.') {
			boolean res =  regRecursionMemo(pattern, text, p-1, t-1, map);
			map.put(key, res);
			return res;
		}
		else if(pattern.charAt(p-1) == '*' && (pattern.charAt(p-2) == text.charAt(t-1) || pattern.charAt(p-2) == '.' )) {
			boolean res = regRecursionMemo(pattern, text, p-2, t, map) || regRecursionMemo(pattern, text, p, t-1, map);
			map.put(key, res);
			return res;
		}
		else if(pattern.charAt(p-1) == '*') {
			boolean res = regRecursionMemo(pattern, text, p-2, t, map);
			map.put(key, res);
			return res;
		}
		else {
			map.put(key, false);
			return false;
		}
	}
	
	// dp
	public static boolean regDp(String pattern, String text, int m, int n) {
		boolean[][] dp = new boolean[m+1][n+1];
		for(int p=0;p<=m;p++) {
			for(int t=0;t<=n;t++) {
				if(p==0 && t ==0) {
					dp[p][t] = true;
				}
				else if(p==0) {
					dp[p][t] = false;
					
				}
				else if(t==0) {
					if(pattern.charAt(p-1) == '*') {
						dp[p][t] = dp[p-2][t];
					}
					else {
						dp[p][t] = false;
					}
				}
				else if(pattern.charAt(p-1) == text.charAt(t-1) || pattern.charAt(p-1) == '.') {
					dp[p][t] = dp[p-1][t-1];
				}
				else if(pattern.charAt(p-1) == '*' && (pattern.charAt(p-2) == text.charAt(t-1) || pattern.charAt(p-2) == '.' )) {
					dp[p][t] = dp[p-2][t] || dp[p][t-1];
				}
				else if(pattern.charAt(p-1) == '*') {
					dp[p][t] = dp[p-2][t];
				}
				else {
					dp[p][t] = false;
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		String pattern = "a*b.*y";
		String text = "ablmy";
		System.out.println(regRecursion(pattern, text, pattern.length(), text.length()));
		Map<String, Boolean> map = new HashMap<>();
		System.out.println(regRecursionMemo(pattern, text, pattern.length(), text.length(), map));
		System.out.println(regDp(pattern, text, pattern.length(), text.length()));
	}

}
