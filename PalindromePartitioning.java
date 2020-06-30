package dp;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioning {
	/*
	 * 
	 * 
	 * dp[i][j] = 0 , i==j
	 * 			= 0 , A.substring(i,j+1)  is palindrome
	 * 			= 1+ min(dp[i][k]+dp[k+1][j])
	 * 				k->i to j-1
	 * 
	 * */
	public static boolean isPalindrome(String A, int i, int j) {
		while(i<j) {
			if(A.charAt(i)!=A.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	// recursion
	public static int palindromePartitionRecursion(String A, int i, int j) {
		if(i>=j) {
			return 0;
		}
		if(isPalindrome(A,i,j)) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for(int k=i;k<j;k++) {
			res = Math.min(res, 1 + palindromePartitionRecursion(A,i,k) + palindromePartitionRecursion(A, k+1, j));
		}
		return res;
	}
	
	// recursion with Memo
		public static int palindromePartitionRecursionMemo(String A, int i, int j, Map<String, Integer> map) {
			String key = i+" "+j;
			if(map.containsKey(key)) {
				return map.get(key);
			}
			if(i>=j) {
				map.put(key,0);
				return 0;
			}
			if(isPalindrome(A,i,j)) {
				map.put(key,0);
				return 0;
			}
			int res = Integer.MAX_VALUE;
			for(int k=i;k<j;k++) {
				res = Math.min(res, 1 + palindromePartitionRecursionMemo(A,i,k, map) + palindromePartitionRecursionMemo(A, k+1, j, map));
			}
			map.put(key,res);
			return res;
		}
	
	// dp
	public static int palindromePartitionDp(String A) {
		int n = A.length();
		int[][] dp = new int[n][n];
		for(int i=0;i<n;i++) {
			dp[i][i] = 0;
		}
		for(int l=2;l<=n;l++) {
			for(int i=0;i<n-l+1;i++) {
				int j=i+l-1;
				if(isPalindrome(A,i,j)) {
					dp[i][j] = 0;
				}
				else {
					dp[i][j] = Integer.MAX_VALUE;
					for(int k=i;k<j;k++) {
						dp[i][j] = Math.min(dp[i][j], 1+dp[i][k]+dp[k+1][j]);
					}
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		String A = "abcbm";
		System.out.println(palindromePartitionRecursion(A,0, A.length()-1));
		Map<String, Integer> map = new HashMap<>();
		System.out.println(palindromePartitionRecursionMemo(A,0, A.length()-1, map));
		System.out.println(palindromePartitionDp(A));
	}

}
