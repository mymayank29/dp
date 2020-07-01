package dp;

import java.util.Arrays;

class Box {
	int h;
	int w;
	int d;
	int area;
	Box(int h, int w, int d) {
		this.h = h;
		this.w = w;
		this.d = d;
		this.area = this.w * this.d;
	}
}
public class BoxStacking {
	
	public static int maxHeight(Box[] arr) {
		Box[] boxes = new Box[arr.length * 3];
		int k=0;
		for(Box box : arr) {
			boxes[k++] = new Box(box.h, Math.min(box.w, box.d), Math.max(box.w, box.d));
			boxes[k++] = new Box(box.w, Math.min(box.h, box.d), Math.max(box.h, box.d));
			boxes[k++] = new Box(box.d, Math.min(box.h, box.w), Math.max(box.h, box.w));
		}
		Arrays.sort(boxes, (b1, b2) -> {
			return b2.area - b1.area;
		});
		
		int[] dp = new int[boxes.length];
		int maxHeight = boxes[0].h;
		dp[0] = boxes[0].h;
		for(int i=1;i<boxes.length;i++) {
			dp[i] = boxes[i].h;
			for(int j=i-1;j>=0;j--) {
				if(boxes[j].w > boxes[i].w && boxes[j].d > boxes[i].d) {
					dp[i] = Math.max(dp[i], dp[j] + boxes[i].h);
				}
			}
			maxHeight = Math.max(maxHeight, dp[i]);
		}
		return maxHeight;
	}

	public static void main(String[] args) {
		 Box[] arr = new Box[4]; 
         arr[0] = new Box(4, 6, 7); 
         arr[1] = new Box(1, 2, 3); 
         arr[2] = new Box(4, 5, 6); 
         arr[3] = new Box(10, 12, 32);
         System.out.println(maxHeight(arr));

	}

}
