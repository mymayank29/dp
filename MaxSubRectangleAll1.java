package dp;

import java.util.Stack;

public class MaxSubRectangleAll1 {
	
	public static int getMaxArea(int[] temp) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<temp.length;i++) {
			if(stack.isEmpty() || temp[i] >= temp[stack.peek()]) {
				stack.push(i);
			}
			else {
				int index = stack.pop();
				int height = temp[index];
				int leftIndex = (stack.isEmpty()) ? -1 : stack.peek() ;
				int rightIndex = i;
				int area = (rightIndex - leftIndex -1) * height;
				maxArea  = Math.max(maxArea, area);
				i--;
			}
		}
		while(!stack.isEmpty()) {
			int index = stack.pop();
			int height = temp[index];
			int leftIndex = (stack.isEmpty()) ? -1 : stack.peek() ;
			int rightIndex = temp.length;
			int area = (rightIndex - leftIndex -1) * height;
			maxArea  = Math.max(maxArea, area);
		}
		return maxArea;
	}
	
	public static int maxSubRecur(int[][] A, int r, int c) {
		int[] temp = new int[c];
		int maxArea = 0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(A[i][j] == 0) {
					temp[j] = 0;
				}
				else {
					temp[j]+=1;
				}
			}
			int max = getMaxArea(temp);
			maxArea = Math.max(maxArea, max);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		  int A[][] = { 
		            { 0, 1, 1, 0 }, 
		            { 1, 1, 1, 1 }, 
		            { 1, 1, 1, 1 }, 
		            { 1, 1, 0, 0 }, 
		        }; 
		  System.out.println(maxSubRecur(A, A.length, A[0].length));

	}

}
