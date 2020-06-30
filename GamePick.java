package dp;
class Item {
	int first;
	int second;
	Item(int first, int second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public String toString() {
		return "Item [first=" + first + ", second=" + second + "]";
	}
	
}
public class GamePick {
	//dp
	public static int gamePick(int[] arr, int n) {
		Item[][] dp = new Item[n][n];
		for(int i=0;i<n;i++) {
			dp[i][i] = new Item(arr[i],0);
		}
		for(int l=2;l<=n;l++) {
			for(int i=0;i<n-l+1;i++) {
				int j=i+l-1;
				int first = Math.max(arr[i] + dp[i+1][j].second, arr[j] + dp[i][j-1].second);
				if(first == arr[i] + dp[i+1][j].second) {
					int second = dp[i+1][j].first;
					dp[i][j] = new Item(first, second);
				}
				else {
					int second = dp[i][j-1].first;
					dp[i][j] = new Item(first, second);
				}
			}
		}
		return dp[0][n-1].first;
	}
	public static void main(String[] args) {
		int[] arr = {3,9,1,2};
		System.out.println(gamePick(arr, arr.length));
	}

}
