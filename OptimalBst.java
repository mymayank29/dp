package dp;

import java.util.HashMap;
import java.util.Map;

public class OptimalBst {
	
	// recursion
	public static int optimalBst(int[] keys, int[] freq, int left, int right, int level) {
		if(left > right) {
			return 0;
		}
		int minCost = Integer.MAX_VALUE;
		for(int i=left;i<=right;i++) {
			int leftBst = optimalBst(keys, freq, left, i-1, level+1);
			int rightBst = optimalBst(keys, freq, i+1, right, level+1);
			minCost = Math.min(minCost, leftBst + rightBst + freq[i]*level);
		}
		return minCost;
	}
	
	
	// recursionMemo
	public static int optimalBstMemo(int[] keys, int[] freq, int left, int right, int level, Map<String, Integer> map) {
		String key = left+" "+right+" "+level;
		if(map.containsKey(key)) {
			
			return map.get(key);
		}
		if(left > right) {
			map.put(key, 0);
			return 0;
		}
		int minCost = Integer.MAX_VALUE;
		for(int i=left;i<=right;i++) {
			int leftBst = optimalBstMemo(keys, freq, left, i-1, level+1, map);
			int rightBst = optimalBstMemo(keys, freq, i+1, right, level+1, map);
			minCost = Math.min(minCost, leftBst + rightBst + freq[i]*level);
		}
		map.put(key, minCost);
		return minCost;
	}
	
	// dp
	// this function helps in not keeping track of level
		public static int sum(int[] freq, int i, int j) {
			int s = 0;
			for(int k=i;k<=j;k++) {
				s+=freq[k];
			}
			return s;
		}
		public static int optimalBstDp(int[] keys, int[] freq) {
			int[][] dp = new int[keys.length][keys.length];
			for(int i=0;i<keys.length;i++) {
				dp[i][i] = freq[i] * 1;
			}
			for(int l=2;l<=keys.length;l++) {
				for(int i=0;i<keys.length-l+1;i++) {
					int j=i+l-1;
					dp[i][j] = Integer.MAX_VALUE;
					for(int k=i;k<=j;k++) {
						int leftBst = (k <= i) ? 0 : dp[i][k-1];
						int rightBst = (k >= j) ? 0 : dp[k+1][j];
						dp[i][j] = Math.min(dp[i][j], leftBst+rightBst+sum(freq,i,j));
					}
					
				}
			}
			return dp[0][keys.length-1];
		}
	
	public static void main(String[] args) {
		int keys[] = {10, 12, 20};  
        int freq[] = {34, 8, 50};
        int keys1[] = {10, 12, 16, 21}; 
        int freq1[] = {4, 2, 6, 3};
        System.out.println(optimalBst(keys, freq, 0, keys.length - 1, 1));
        Map<String, Integer> map = new HashMap<>();
        System.out.println(optimalBstMemo(keys1, freq1, 0, keys1.length - 1, 1, map));
        System.out.println(optimalBstDp(keys,freq));
	}

}
