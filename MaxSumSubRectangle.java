package dp;

import java.util.Arrays;

public class MaxSumSubRectangle {
	
	public static int kadane(int[] temp) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for(int n: temp) {
			sum+= n;
			maxSum = Math.max(maxSum, sum);
			if(sum<0) {
				sum = 0;
			}
		}
		return maxSum;
	}
	
	public static int maxSubRectangle(int[][] arr) {
		int[] temp = new int[arr.length];
		int maxRecSum = Integer.MIN_VALUE;
		for(int left=0; left<arr[0].length; left++) {
			Arrays.fill(temp, 0);
			for(int right = left; right < arr[0].length; right++) {
				for(int k=0;k<arr.length;k++) {
					temp[k] += arr[k][right];
				}
				int maxRec = kadane(temp);
				maxRecSum = Math.max(maxRecSum, maxRec);
				
			}
		}
		return maxRecSum;
	}

	public static void main(String[] args) {
		 int arr[][] = {{1, 2, -1, -4, -20},  
				 		{-8, -3, 4, 2, 1},  
				 		{3, 8, 10, 1, 3},  
				 		{-4, -1, 1, 7, -6}}; 
		 System.out.println(maxSubRectangle(arr));

	}

}
