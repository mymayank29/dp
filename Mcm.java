package dp;
import java.util.*;

class Matrix {
	int r;
	int c;
	Matrix(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Mcm {
	/*
	 * 
	 * dp[i][j] =  0, i==j
	 * 			= Math.min(dp[i][k] + dp[k+1][j] + mat[i].r * mat[k].c * mat[j].c)
	 * 			  k from i to j-1, 
	 * 
	 * */
	
	public static int minMatRecursion(Matrix[] mat, int i, int j) {
		if(i==j) {
			return 0;
		}
		int minVal = Integer.MAX_VALUE;
		for(int k = i;k <= j-1; k++) {
			minVal = Math.min(minVal, minMatRecursion(mat,i,k)+
					minMatRecursion(mat,k+1,j)+ (mat[i].r*mat[k].c*mat[j].c));
		}
		return minVal;
	}
	public static int minMatRecursion(Matrix[] mat) {
		return minMatRecursion(mat, 0, mat.length-1);
	}
	
	// recursion with memo
	public static int minMatRecursionMemo(Matrix[] mat, int i, int j, Map<String, Integer> map) {
		if(map.containsKey(i+" "+j)) {
			return map.get(i+" "+j);
		}
		else if(i==j) {
			map.put(i+" "+j, 0);
			return 0;
		}
		int minVal = Integer.MAX_VALUE;
		for(int k = i;k <= j-1; k++) {
			minVal = Math.min(minVal, minMatRecursionMemo(mat,i,k,map)+
					minMatRecursionMemo(mat,k+1,j,map)+ (mat[i].r*mat[k].c*mat[j].c));
		}
		map.put(i+" "+j, minVal);
		return minVal;
	}
	
	public static int minMatRecursionMemo(Matrix[] mat) {
		Map<String, Integer> map = new HashMap<>();
		return minMatRecursionMemo(mat, 0, mat.length-1, map);
	}
	
	// dp
	public static int minMatRecursionDp(Matrix[] mat) {
		int n = mat.length;
		int[][] dp = new int[n][n];
		for(int i=0;i<n;i++) {
			dp[i][i] = 0;
		}
		for(int l=2;l<=n;l++) {
			for(int i=0;i<n-l+1;i++) {
				int j = i+l-1;
				
				dp[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<=j-1;k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + (mat[i].r*mat[k].c*mat[j].c));
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		Matrix m1 = new Matrix(2,3);
		Matrix m2 = new Matrix(3,6);
		Matrix m3 = new Matrix(6,4);
		Matrix m4 = new Matrix(4,5);
		Matrix[] mat = {m1,m2,m3,m4};
		System.out.println(minMatRecursion(mat));
		System.out.println(minMatRecursionMemo(mat));
		System.out.println(minMatRecursionDp(mat));
	}

}
