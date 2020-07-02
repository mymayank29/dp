package dp;

public class TotalNoWithoutConsecutive1s {
	/*
	 * 
	 * dp[n] = 2				, n=1
	 * 		 = 3,				, n=2
	 * 		 = dp[n-1]+dp[n-2]
	 * 
	 * */
	// recursion
	public static int totalNo(int n) {
		if(n==0) {
			return 0;
		}
		else if(n==1) {
			return 2;
		}
		else if(n==2) {
			return 3;
		}
		return totalNo(n-1) + totalNo(n-2);
	}
	
	// dp
	public static int totalNoDp(int n) {
		if(n==0) {
			return 0;
		}
		else if(n==1) {
			return 2;
		}
		else if(n==2) {
			return 3;
		}
		int a = 2;
		int b = 3;
		for(int i=3;i<=n;i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return b;
	}
	public static void main(String[] args) {
		System.out.println(totalNo(4));
		System.out.println(totalNoDp(4));
	}

}
