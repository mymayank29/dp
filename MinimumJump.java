package dp;

import java.util.HashMap;
import java.util.Map;

public class MinimumJump {
	/*
	 * 
	 *	
	 *	dp[i][j] -> min jump from i to j
	 *
	 *  dp[i][j] = 0, i=j
	 *  		 = 1 + min (dp[k][j])
	 *  					k-> i+1 to j && k<= i+arr[i]
	 *  
	 *  
	 *  Another recursion
	 *  dp[j] = 0, j=0
	 *  		1 + min(dp[k])
	 *  				k-> j-1 to 0 && j<= k+arr[k]
	 * 
	 * 
	 * one more optimised solution O(n) exists
	 * 
	 * */
	
	// recursion with 1st method
	public static int minJumpRecursion(int[] arr, int i, int j) {
		if(i==j) {
			return 0;
		}
		if(arr[i] == 0) {
			return Integer.MAX_VALUE;
		}
		int minJump = Integer.MAX_VALUE;
		for(int k=i+1;k<=j;k++) {
			if(k<=i+arr[i] ) {
				minJump = Math.min(minJump, minJumpRecursion(arr,k,j));
			}
		}
		if(minJump!=Integer.MAX_VALUE) {
			return minJump+1;
		}
		return minJump;
	}
	
	
	// recursion with 2nd method
	public static int minJumpRecursion2(int[] arr, int j) {
		if(j==0) {
			return 0;
		}
		int minJump = Integer.MAX_VALUE;
		for(int k=j-1;k>=0;k--) {
			if(j <= k+arr[k] ) {
				minJump = Math.min(minJump, minJumpRecursion2(arr,k));
			}
		}
		if(minJump!=Integer.MAX_VALUE) {
			return minJump+1;
		}
		return minJump;
	}
	
	// recursion with Memo
	public static int minJumpRecursion2Memo(int[] arr, int j, Map<Integer, Integer> map) {
		if(map.containsKey(j)) {
			return map.get(j);
		}
		if(j==0) {
			map.put(j, 0);
			return 0;
		}
		int minJump = Integer.MAX_VALUE;
		for(int k=j-1;k>=0;k--) {
			if(j <= k+arr[k] ) {
				minJump = Math.min(minJump, minJumpRecursion2Memo(arr,k, map));
			}
		}
		if(minJump!=Integer.MAX_VALUE) {
			map.put(j, minJump+1);
			return minJump+1;
		}
		map.put(j, minJump);
		return minJump;
	}
	
	// Dp
	public static int minJumpDp(int[] arr, int n) {
		int[] dp = new int[n];
		dp[0] = 0;
		if(arr[0] == 0) {
			return Integer.MAX_VALUE;
		}
		for(int i=1;i<n;i++) {
			dp[i] = Integer.MAX_VALUE;
			for(int j=i-1;j>=0;j--) {
				if(j+arr[j] >= i) {
					dp[i] = Math.min(dp[i], dp[j]);
				}
			}
			if(dp[i]!=Integer.MAX_VALUE) {
				dp[i]+=1;
			}
		}
		return dp[n-1];
	}
	
	// optimised
	public static int minJump(int[] arr, int n) {
		if(n==0 || arr[0] == 0) {
			return Integer.MAX_VALUE;
		}
		int steps = arr[0];
		int jump = 0;
		int currLadder = arr[0];
		int j = 1;
		while(j<n) {
			if(j==n-1) {
				return jump+1;
			}
			steps--;
			int ladder = j + arr[j];
			if(currLadder < ladder) {
				currLadder = ladder;
			}
		
			if(steps == 0) {
				if(currLadder < j) {
					return Integer.MAX_VALUE;
				}
				jump++;
				steps = currLadder - j;
			}
			j++;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 }; 
        int n = arr.length; 
        System.out.println(minJumpRecursion(arr,0,n-1));
        System.out.println(minJumpRecursion2(arr,n-1));
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(minJumpRecursion2Memo(arr,n-1, map));
        System.out.println(minJumpDp(arr,n));
        System.out.println(minJump(arr,n));
	}

}
