package dp;

public class Lis {
	
	public static int lis(int[] arr, int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int maxVal = 0;
		for(int i=1;i<n;i++) {
			dp[i] = 1;
			for(int j=i-1;j>=0;j--) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
			maxVal = Math.max(maxVal, dp[i]);
		}
		return maxVal;
	}

	public static void main(String[] args) {
		int[] arr = {3,4,-1,0,6,2,3};
		System.out.println(lis(arr,arr.length));

	}

}
