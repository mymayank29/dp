package dp;

public class BurstBalloon {
	
	//https://www.youtube.com/watch?v=uG_MtaCJIrM
	public static int burstBalloon(int[] A) {
		int[] points = new int[A.length+2];
		points[0] = 1;
		points[points.length-1] = 1;
		for(int i=1;i <= points.length-2; i++) {
			points[i] = A[i-1];
		}
		int[][] dp = new int[points.length][points.length];
		for(int i=1;i<=points.length-2;i++) {
			dp[i][i] = points[i-1] * points[i] * points[i+1];
		}
		for(int l=2;l<=A.length;l++) {
			for(int i=1;i<points.length-l;i++) {
				int j = i+l-1;
				for(int k=i;k<=j;k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + (points[i-1] * points[k] * points[j+1]) + dp[k+1][j]);
				}
			}
		}
		return dp[1][A.length];
		
	}

	public static void main(String[] args) {
		int A[] = { 1, 2, 3, 4, 5 };
		System.out.println(burstBalloon(A));

	}

}
