package dp;

public class BitonicSequence {
	
	public static int lbs(int[] arr, int n) {
		int[] dp = new int[n];
		int maxVal = 0;
		dp[0] = 1;
		for(int i=1;i<n;i++) {
			dp[i] = 1;
			for(int j=i-1;j>=0;j--) {
				if(arr[j]< arr[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
		}
		int[] rdp = new int[n];
		rdp[n-1] = 1;
		for(int i=n-2;i>=0;i--) {
			rdp[i] = 1;
			for(int j=i+1;j<n;j++) {
				if(arr[j] < arr[i]) {
					rdp[i] = Math.max(rdp[i], 1 + rdp[j]);
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			maxVal = Math.max(maxVal, dp[i]+rdp[i]-1);
		}
		return maxVal;
	}

	public static void main(String[] args) {
		int[] arr = {2,-1,4,3,5,-1,3,2};
		System.out.println(lbs(arr,arr.length));

	}

}
