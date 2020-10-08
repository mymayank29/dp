package dp;

class ItemSquare {
	int v;
	int h;
	ItemSquare(int v, int h) {
		this.v = v;
		this.h = h;
	}
	@Override
	public String toString() {
		return "("+v+", "+h+")";
	}
}
public class MaxSubSquareSideX {
	
	public static int maxSubSquare(char[][] mat, int r, int c) {
		ItemSquare[][] items = new ItemSquare[r][c];
		
		items[0][0] = mat[0][0] == 'O' ? new ItemSquare(0,0) : new ItemSquare(1,1);
		// setting 1st row
		for(int i=1;i<c;i++) {
			if(mat[0][i] == 'O') {
				items[0][i] = new ItemSquare(0,0);
			}
			else {
				items[0][i] = new ItemSquare(1, 1+items[0][i-1].h);
			}
		}
		// setting 1st col
		for(int i=1;i<r;i++) {
			if(mat[i][0] == 'O') {
				items[i][0] = new ItemSquare(0,0);
			}
			else {
				items[i][0] = new ItemSquare(1 + items[i-1][0].v, 1);
			}
		}
		// assigning rest of the matrix
		for(int i=1;i<r;i++) {
			for(int j=1;j<c;j++) {
				if(mat[i][j] == 'O') {
					items[i][j] = new ItemSquare(0,0);
				}
				else {
					items[i][j] = new ItemSquare(1+items[i-1][j].v, 1+items[i][j-1].h);
				}
			}
		}
		
//		for(int i=0;i<r;i++) {
//			for(int j=0;j<c;j++) {
//				System.out.print(items[i][j]);
//			}
//			System.out.println();
//		}
		
		int maxVal = 0;
		for(int i=r-1;i>=0;i--) {
			for(int j=c-1;j>=0;j--) {
				int minVal = Math.min(items[i][j].v, items[i][j].h);
				while(minVal > maxVal) {
					ItemSquare itemVertical = items[i-minVal+1][j];
					ItemSquare itemHorizontal = items[i][j-minVal+1];
					if(itemVertical.h >= minVal && itemHorizontal.v >= minVal) {
						maxVal = minVal;
					}
					minVal--;
				}
			}
		}
		return maxVal;
	}

	public static void main(String[] args) {
		char[][] mat = {
				{'O','O','O','O','X'},
				{'X','O','X','X','X'},
				{'X','O','X','O','X'},
				{'X','X','X','X','X'},
				{'O','O','X','X','X'},
		};
		System.out.println(maxSubSquare(mat,mat.length, mat[0].length));

	}

}
