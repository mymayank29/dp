package dp;

public class MaxProfitKTransaction {
	/*
	 * 
	 * 
	 * dp[k][n] = 0, k==0 || n==0
	 * 			=  Math.max(dp[k][n-1], Math.max(price[n-1] - price[j] + dp[k-1][j]))
	 * 										j -> n-2 to 0
	 * */
	// recursion
	public static int maxProfitRecursion(int[] price, int n, int k) {
		if(n==0 || k == 0) {
			return 0;
		}
		int maxProfit = maxProfitRecursion(price, n-1,k);
		int profit = 0;
		for(int j=n-2;j>=0;j--) {
			profit = Math.max(profit, price[n-1] - price[j] + maxProfitRecursion(price, j, k-1));
		}
		
		return Math.max(maxProfit, profit);
	}
	
	// dp
	public static int maxProfitDp(int[] price, int n, int k) {
		int[][] dp = new int[k+1][n+1];
		for(int i=0;i<=k;i++) {
			for(int j=0;j<=n;j++) {
				if(i == 0 || j == 0) {
					dp[i][j] = 0;
				}
				else {
					int profit = 0;
					for(int l= j-2;l>=0;l--) {
						profit = Math.max(profit, price[j-1]-price[l] + dp[i-1][l]);
					}
					dp[i][j] = Math.max(profit, dp[i][j-1]);
				}
			}
		}
		return dp[k][n];
	}
	
	// optimised dp
	// refer tushar roy video
	public static int maxProfitDp2(int[] price, int n, int k) {
		int[][] dp = new int[k+1][n+1];
		for(int i=0;i<=k;i++) {
			int maxDiff = Integer.MIN_VALUE;
			for(int j=0;j<=n;j++) {
				if(i == 0 || j == 0 || j==1) {
					dp[i][j] = 0;
				}
				else {
					maxDiff = Math.max(maxDiff, dp[i-1][j-1] - price[j-2]);
					dp[i][j] = Math.max(dp[i][j-1], price[j-1]+maxDiff);
				}
			}
		}
		return dp[k][n];
	}

	public static void main(String[] args) {
		 int k = 3; 
	        int[] price = {100, 30, 15, 10, 8, 25, 80}; 
	        int n = price.length; 
	        System.out.println(maxProfitRecursion(price, n, k)); 
	        System.out.println(maxProfitDp(price, n, k)); 
	        System.out.println(maxProfitDp2(price, n, k));
	}

}
