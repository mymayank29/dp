package dp;

public class MaxSumIncreasingSubsequence {
	
	/*
	 * dp[i] -> max sum upto nth index including nth index element
	 * dp[i] = arr[i] , i=0
	 * 		 = arr[i] + max(dp[k])
	 * 					k-> i-1 to 0 && arr[k]< arr[i]; 
	 * */
	static int maxValueGlobal = Integer.MIN_VALUE;
	public static int maxSumISRecursionHelper(int[] arr, int i) {
		if(i==0) {
			maxValueGlobal = Math.max(maxValueGlobal, arr[i]);
			return arr[i];
		}
		int max = arr[i];
		for(int k=i-1; k>=0; k--) {
			if(arr[k] < arr[i]) {
				max = Math.max(max,arr[i]+maxSumISRecursionHelper(arr,k));
			}
		}
		maxValueGlobal = Math.max(maxValueGlobal, max);
		return max;
	}
	// recursion
	public static int maxSumISRecursion(int[] arr, int n) {
		for(int i=0;i<n;i++) {
			maxSumISRecursionHelper(arr, i);
		}
		 return maxValueGlobal;
	}
	
	
	// dp
	public static int maxSumISDp(int[] arr, int n) {
		int[] dp = new int[n];
		dp[0] = arr[0];
		int maxVal = Integer.MIN_VALUE;
		for(int i=1;i<n;i++) {
			dp[i] = arr[i];
			for(int j=i-1;j>=0;j--) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], arr[i] + dp[j]);
				}
			}
			maxVal = Math.max(maxVal, dp[i]);
		}
		return maxVal;
	}
	public static void main(String[] args) {
		int arr[] = {1, 101, 2, 3, 100, 4, 5}; 
        int n = arr.length;
        System.out.println(maxSumISRecursion(arr,n));
        System.out.println(maxSumISDp(arr,n));
	}

}
