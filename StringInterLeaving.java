package dp;

public class StringInterLeaving {
	/*
	 * return false if n+m != k
	 * dp[n][m] = true,  n==0 && m==0 
	 * 			= dp[n][m-1]	, B[m-1] == C[m-1]  && n==0
	 * 			= false			, n==0
	 * 			= dp[n-1][m]	, A[n-1]==C[n-1] && m==0
	 * 			= false			, m==0
	 * 			= dp[n-1][m]	, A[n-1]==C[n+m-1]
	 * 			= dp[n][m-1]	, B[m-1]==C[n+m-1]
	 * 			= false			, else
	 * */
	
	// recursion
	public static boolean isInterleaving(String A, String B, String C, int n, int m, int k) {
		// System.out.println(n+" "+m+" "+k);
		if((n+m) != k) {
			return false;
		}
		else if(n==0 && m==0) {
			return true;
		}
		else if(n==0 && B.charAt(m-1) == C.charAt(k-1)) {
			return isInterleaving(A,B,C,n,m-1,k-1);
		}
		else if(m==0 && A.charAt(n-1) == C.charAt(k-1) ) {
			return isInterleaving(A,B,C,n-1,m,k-1);
		}
		else if(n>0 && A.charAt(n-1) == C.charAt(k-1)) {
			return isInterleaving(A,B,C,n-1,m,k-1);
		}
		else if(m>0 && B.charAt(m-1) == C.charAt(k-1)) {
			return isInterleaving(A,B,C,n,m-1,k-1);
		}
		return false;
	}
	
	// dp
	public static boolean isInterleavingDp(String A, String B, String C, int n, int m, int k) {
		if((n+m) != k) {
			return false;
		}
		boolean[][] dp = new boolean[n+1][m+1];
		dp[0][0] = true;
		for(int i=1;i<=n;i++) {
			dp[i][0] = (A.charAt(i-1) == C.charAt(i-1)) ? dp[i-1][0] : false;
		}
		for(int i=1;i<=m;i++) {
			dp[0][i] = (B.charAt(i-1) == C.charAt(i-1)) ? dp[0][i-1] : false;
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if(A.charAt(i-1) == C.charAt(i+j-1)) {
					dp[i][j] = dp[i-1][j];
				}
				else if(B.charAt(j-1) == C.charAt(i+j-1)) {
					dp[i][j] = dp[i][j-1];
				}
				else {
					dp[i][j] = false;
				}
			}
		}
		return dp[n][m];
	}
	public static void main(String[] args) {
		String A = "aab";
		String B = "axy";
		String C = "aaxaby";
		System.out.println(isInterleaving(A,B,C, A.length(), B.length(), C.length()));
		System.out.println(isInterleavingDp(A,B,C, A.length(), B.length(), C.length()));

	}

}
