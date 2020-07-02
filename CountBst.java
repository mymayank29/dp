package dp;

public class CountBst {
	/*
	 * 
		n=1 -> 1 = dp[1]
		n=2 -> 2 = dp[2]
		n=3 -> 5 = dp[3]
		n==4
		10, 11, 12 , 13
		
		if root is
		10 -> dp[All that are greator than 10] * d[All that are less than 10] +
		11 -> dp[All that are greator than 11] * d[All that are less than 11] +
		12 -> dp[All that are greator than 12] * d[All that are less than 12] +
		13 -> dp[All that are greator than 13] * d[All that are less than 13]
		
		n=4 = 5*1 + 2*1 + 1*2 + 1*5 = 14   
		    = dp[4-1]*dp[0] + dp[4-2]*dp[1] + dp[4-3] * dp[2] + dp[4-4]*dp[3]
			= dp[4-1]*dp[1-1] + dp[4-2]*dp[2-1] + dp[4-3] * dp[3-1] + dp[4-4]*dp[4-1]
			
			// one formula
			for(int i=1;i<=n;i++) {
				sum+= dp[n-i]*dp[i-1];
			}
			
			
			//other formula
			if(n%2 == 0) {
				for(int i=1;i<=n/2;i++) {
					sum+= dp[n-i]*dp[i-1];
				}
				return sum*2;
			} 
			else {
				for(int i=1;i<=n/2;i++) {
					sum+= dp[n-i]*dp[i-1];
				}
				return (sum*2) + dp[n/2]*dp[n/2];
			}
	 * 
	 * 
	 * 
	 * */
	
	
	// dp 
	public static int countBst(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				dp[i] += (dp[i-j] * dp[j-1]);
			}
		}
		return dp[n];
	}

	// dp
		public static int countBstOptimised(int n) {
			int[] dp = new int[n+1];
			dp[0] = 1;
			dp[1] = 1;
			for(int i=2;i<=n;i++) {
				for(int j=1;j<=i/2;j++) {
					dp[i] += (dp[i-j] * dp[j-1]);
				}
				dp[i]*=2;
				if(i%2 != 0) {
					dp[i]+=(dp[i/2]*dp[i/2]);
				}
			}
			return dp[n];
		}

	public static void main(String[] args) {
		System.out.println(countBst(4));
		System.out.println(countBstOptimised(4));
	}

}
