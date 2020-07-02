package dp;

public class MaxSumSubsequenceNonAdjacent {
	/*
	 * 
	 * dp[n] = max(dp[n-1]incl , arr[n] + dp[n-1]excl)
	 * 
	 * */
	
	public static int maxSum(int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		if(arr.length == 1) {
			return arr[0];
		}
		int incl = arr[0];
		int excl = 0;
		for(int i=1;i<arr.length;i++) {
			int temp = incl;
			incl = Math.max(incl, arr[i]+excl);
			excl = temp;
		}
		return incl;
	}
	public static void main(String[] args) {
		int[] arr = {4,1,1,4,2,1};
		System.out.println(maxSum(arr));
	}

}
